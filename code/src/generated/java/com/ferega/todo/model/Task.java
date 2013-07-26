package com.ferega.todo.model;

import com.dslplatform.patterns.*;
import com.dslplatform.client.*;
import com.fasterxml.jackson.annotation.*;

public class Task implements java.io.Serializable, AggregateRoot {
    public Task() {
        _serviceLocator = Bootstrap.getLocator();
        _domainProxy = _serviceLocator.resolve(DomainProxy.class);
        _crudProxy = _serviceLocator.resolve(CrudProxy.class);
        this.ID = 0;
        this.userID = "";
        this.name = "";
        this.description = "";
        this.priority = 0;
    }

    private transient final ServiceLocator _serviceLocator;
    private transient final DomainProxy _domainProxy;
    private transient final CrudProxy _crudProxy;

    private String URI;

    @JsonProperty("URI")
    public String getURI() {
        return this.URI;
    }

    @Override
    public int hashCode() {
        return URI != null ? URI.hashCode() : super.hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;
        final Task other = (Task) obj;

        return URI != null && URI.equals(other.URI);
    }

    @Override
    public String toString() {
        return URI != null ? "Task(" + URI + ')' : "new Task(" + super.hashCode() + ')';
    }

    private static final long serialVersionUID = 0x0097000a;

    public Task(
            final com.ferega.todo.model.User user,
            final String name,
            final String description,
            final int priority) {
        _serviceLocator = Bootstrap.getLocator();
        _domainProxy = _serviceLocator.resolve(DomainProxy.class);
        _crudProxy = _serviceLocator.resolve(CrudProxy.class);
        setUser(user);
        setName(name);
        setDescription(description);
        setPriority(priority);
    }

    @JsonCreator private Task(
            @JacksonInject("_serviceLocator") final ServiceLocator _serviceLocator,
            @JsonProperty("URI") final String URI ,
            @JsonProperty("ID") final int ID,
            @JsonProperty("userID") final String userID,
            @JsonProperty("userURI") final String userURI,
            @JsonProperty("name") final String name,
            @JsonProperty("description") final String description,
            @JsonProperty("priority") final int priority) {
        this._serviceLocator = _serviceLocator;
        this._domainProxy = _serviceLocator.resolve(DomainProxy.class);
        this._crudProxy = _serviceLocator.resolve(CrudProxy.class);
        this.URI = URI;
        this.ID = ID;
        this.userID = userID == null ? "" : userID;
        this.userURI = userURI == null ? null : userURI;
        this.name = name == null ? "" : name;
        this.description = description == null ? "" : description;
        this.priority = priority;
    }

    private int ID;

    @JsonProperty("ID")
    public int getID() {
        return ID;
    }

    private Task setID(final int value) {
        this.ID = value;

        return this;
    }

    public static Task find(final String uri) throws java.io.IOException {
        return find(uri, Bootstrap.getLocator());
    }
    public static Task find(final String uri, final ServiceLocator locator) throws java.io.IOException {
        try {
            return (locator != null ? locator : Bootstrap.getLocator()).resolve(CrudProxy.class).read(Task.class, uri).get();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }
    public static java.util.List<Task> find(final Iterable<String> uris) throws java.io.IOException {
        return find(uris, Bootstrap.getLocator());
    }
    public static java.util.List<Task> find(final Iterable<String> uris, final ServiceLocator locator) throws java.io.IOException {
        try {
            return (locator != null ? locator : Bootstrap.getLocator()).resolve(DomainProxy.class).find(Task.class, uris).get();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }
    public static java.util.List<Task> findAll() throws java.io.IOException {
        return findAll(null, null, Bootstrap.getLocator());
    }
    public static java.util.List<Task> findAll(final ServiceLocator locator) throws java.io.IOException {
        return findAll(null, null, locator);
    }
    public static java.util.List<Task> findAll(final Integer limit, final Integer offset) throws java.io.IOException {
        return findAll(limit, offset, Bootstrap.getLocator());
    }
    public static java.util.List<Task> findAll(final Integer limit, final Integer offset, final ServiceLocator locator) throws java.io.IOException {
        try {
            return (locator != null ? locator : Bootstrap.getLocator()).resolve(DomainProxy.class).findAll(Task.class, limit, offset, null).get();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }
    public static java.util.List<Task> search(final Specification<Task> specification) throws java.io.IOException {
        return search(specification, null, null, Bootstrap.getLocator());
    }
    public static java.util.List<Task> search(final Specification<Task> specification, final ServiceLocator locator) throws java.io.IOException {
        return search(specification, null, null, locator);
    }
    public static java.util.List<Task> search(final Specification<Task> specification, final Integer limit, final Integer offset) throws java.io.IOException {
        return search(specification, limit, offset, Bootstrap.getLocator());
    }
    public static java.util.List<Task> search(final Specification<Task> specification, final Integer limit, final Integer offset, final ServiceLocator locator) throws java.io.IOException {
        try {
            return (locator != null ? locator : Bootstrap.getLocator()).resolve(DomainProxy.class).search(specification, limit, offset, null).get();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }
    public static long count() throws java.io.IOException {
        return count(Bootstrap.getLocator());
    }
    public static long count(final ServiceLocator locator) throws java.io.IOException {
        try {
            return (locator != null ? locator : Bootstrap.getLocator()).resolve(DomainProxy.class).count(Task.class).get().longValue();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }
    public static long count(final Specification<Task> specification) throws java.io.IOException {
        return count(specification, Bootstrap.getLocator());
    }
    public static long count(final Specification<Task> specification, final ServiceLocator locator) throws java.io.IOException {
        try {
            return (locator != null ? locator : Bootstrap.getLocator()).resolve(DomainProxy.class).count(specification).get().longValue();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }
    private void updateWithAnother(final com.ferega.todo.model.Task result) {
        this.URI = result.URI;

        this.userID = result.userID;
        this.user = result.user;
        this.userURI = result.userURI;
        this.name = result.name;
        this.description = result.description;
        this.priority = result.priority;
        this.ID = result.ID;
    }
    public Task persist() throws java.io.IOException {
        if (this.getUserURI() == null) {
            throw new IllegalArgumentException("Cannot persist instance of 'com.ferega.todo.model.Task' because reference 'user' was not assigned");
        }
        final Task result;
        try {
            result = this.URI == null ? _crudProxy.create(this).get() : _crudProxy.update(this).get();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
        this.updateWithAnother(result);
        return this;
    }
    public Task delete() throws java.io.IOException {
        try {
            return _crudProxy.delete(Task.class, URI).get();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }

    private String userID;

    @JsonProperty("userID")
    public String getUserID() {
        return userID;
    }

    private Task setUserID(final String value) {
        if(value == null) throw new IllegalArgumentException("Property \"userID\" cannot be null!");
        this.userID = value;

        return this;
    }

    private String userURI;

    @JsonProperty("userURI")
    public String getUserURI() {
        return this.userURI;
    }

    private com.ferega.todo.model.User user;

    @JsonIgnore
    public com.ferega.todo.model.User getUser() throws java.io.IOException {
        if (user != null && !user.getURI().equals(userURI) || user == null && userURI != null)
            try {
                user = _crudProxy.read(com.ferega.todo.model.User.class, userURI).get();
            } catch (final InterruptedException e) {
                throw new java.io.IOException(e);
            } catch (final java.util.concurrent.ExecutionException e) {
                throw new java.io.IOException(e);
            }
        return user;
    }

    public Task setUser(final com.ferega.todo.model.User value) {
        if(value == null) throw new IllegalArgumentException("Property \"user\" cannot be null!");

        if(value != null && value.getURI() == null) throw new IllegalArgumentException("Reference \"model.User\" for property \"user\" must be persisted before it's assigned");
        this.user = value;

        this.userURI = value.getURI();

        this.userID = value.getUsername();
        return this;
    }

    private String name;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public Task setName(final String value) {
        if(value == null) throw new IllegalArgumentException("Property \"name\" cannot be null!");
        this.name = value;

        return this;
    }

    private String description;

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    public Task setDescription(final String value) {
        if(value == null) throw new IllegalArgumentException("Property \"description\" cannot be null!");
        this.description = value;

        return this;
    }

    private int priority;

    @JsonProperty("priority")
    public int getPriority() {
        return priority;
    }

    public Task setPriority(final int value) {
        this.priority = value;

        return this;
    }

public static class findByUser implements java.io.Serializable, Specification<Task> {
    public findByUser(
             final String username) {
        setUsername(username);
    }

    public findByUser() {
        this.username = "";
    }

    private static final long serialVersionUID = 0x0097000a;

    private String username;

    public String getUsername() {
        return username;
    }

    public findByUser setUsername(final String value) {
        if(value == null) throw new IllegalArgumentException("Property \"username\" cannot be null!");
        this.username = value;

        return this;
    }

        public java.util.List<Task> search() throws java.io.IOException {
            return search(null, null, Bootstrap.getLocator());
        }
        public java.util.List<Task> search(final ServiceLocator locator) throws java.io.IOException {
            return search(null, null, locator);
        }
        public java.util.List<Task> search(final Integer limit, final Integer offset) throws java.io.IOException {
            return search(limit, offset, Bootstrap.getLocator());
        }
        public java.util.List<Task> search(final Integer limit, final Integer offset, final ServiceLocator locator) throws java.io.IOException {
            try {
                return (locator != null ? locator : Bootstrap.getLocator()).resolve(DomainProxy.class).search(this, limit, offset, null).get();
            } catch (final InterruptedException e) {
                throw new java.io.IOException(e);
            } catch (final java.util.concurrent.ExecutionException e) {
                throw new java.io.IOException(e);
            }
        }
        public long count() throws java.io.IOException {
            return count(Bootstrap.getLocator());
        }
        public long count(final ServiceLocator locator) throws java.io.IOException {
            try {
                return (locator != null ? locator : Bootstrap.getLocator()).resolve(DomainProxy.class).count(this).get().longValue();
            } catch (final InterruptedException e) {
                throw new java.io.IOException(e);
            } catch (final java.util.concurrent.ExecutionException e) {
                throw new java.io.IOException(e);
            }
        }
}
}
