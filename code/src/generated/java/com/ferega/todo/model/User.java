package com.ferega.todo.model;

import com.dslplatform.patterns.*;
import com.dslplatform.client.*;
import com.fasterxml.jackson.annotation.*;

public class User implements java.io.Serializable, AggregateRoot {
    public User() {
        _serviceLocator = Bootstrap.getLocator();
        _domainProxy = _serviceLocator.resolve(DomainProxy.class);
        _crudProxy = _serviceLocator.resolve(CrudProxy.class);
        this.username = "";
        this.passhash = "";
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
        final User other = (User) obj;

        return URI != null && URI.equals(other.URI);
    }

    @Override
    public String toString() {
        return URI != null ? "User(" + URI + ')' : "new User(" + super.hashCode() + ')';
    }

    private static final long serialVersionUID = 0x0097000a;

    public User(
            final String username,
            final String passhash) {
        _serviceLocator = Bootstrap.getLocator();
        _domainProxy = _serviceLocator.resolve(DomainProxy.class);
        _crudProxy = _serviceLocator.resolve(CrudProxy.class);
        setUsername(username);
        setPasshash(passhash);
    }

    @JsonCreator private User(
            @JacksonInject("_serviceLocator") final ServiceLocator _serviceLocator,
            @JsonProperty("URI") final String URI ,
            @JsonProperty("username") final String username,
            @JsonProperty("passhash") final String passhash) {
        this._serviceLocator = _serviceLocator;
        this._domainProxy = _serviceLocator.resolve(DomainProxy.class);
        this._crudProxy = _serviceLocator.resolve(CrudProxy.class);
        this.URI = URI;
        this.username = username == null ? "" : username;
        this.passhash = passhash == null ? "" : passhash;
    }

    public static User find(final String uri) throws java.io.IOException {
        return find(uri, Bootstrap.getLocator());
    }
    public static User find(final String uri, final ServiceLocator locator) throws java.io.IOException {
        try {
            return (locator != null ? locator : Bootstrap.getLocator()).resolve(CrudProxy.class).read(User.class, uri).get();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }
    public static java.util.List<User> find(final Iterable<String> uris) throws java.io.IOException {
        return find(uris, Bootstrap.getLocator());
    }
    public static java.util.List<User> find(final Iterable<String> uris, final ServiceLocator locator) throws java.io.IOException {
        try {
            return (locator != null ? locator : Bootstrap.getLocator()).resolve(DomainProxy.class).find(User.class, uris).get();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }
    public static java.util.List<User> findAll() throws java.io.IOException {
        return findAll(null, null, Bootstrap.getLocator());
    }
    public static java.util.List<User> findAll(final ServiceLocator locator) throws java.io.IOException {
        return findAll(null, null, locator);
    }
    public static java.util.List<User> findAll(final Integer limit, final Integer offset) throws java.io.IOException {
        return findAll(limit, offset, Bootstrap.getLocator());
    }
    public static java.util.List<User> findAll(final Integer limit, final Integer offset, final ServiceLocator locator) throws java.io.IOException {
        try {
            return (locator != null ? locator : Bootstrap.getLocator()).resolve(DomainProxy.class).findAll(User.class, limit, offset, null).get();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }
    public static java.util.List<User> search(final Specification<User> specification) throws java.io.IOException {
        return search(specification, null, null, Bootstrap.getLocator());
    }
    public static java.util.List<User> search(final Specification<User> specification, final ServiceLocator locator) throws java.io.IOException {
        return search(specification, null, null, locator);
    }
    public static java.util.List<User> search(final Specification<User> specification, final Integer limit, final Integer offset) throws java.io.IOException {
        return search(specification, limit, offset, Bootstrap.getLocator());
    }
    public static java.util.List<User> search(final Specification<User> specification, final Integer limit, final Integer offset, final ServiceLocator locator) throws java.io.IOException {
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
            return (locator != null ? locator : Bootstrap.getLocator()).resolve(DomainProxy.class).count(User.class).get().longValue();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }
    public static long count(final Specification<User> specification) throws java.io.IOException {
        return count(specification, Bootstrap.getLocator());
    }
    public static long count(final Specification<User> specification, final ServiceLocator locator) throws java.io.IOException {
        try {
            return (locator != null ? locator : Bootstrap.getLocator()).resolve(DomainProxy.class).count(specification).get().longValue();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }
    private void updateWithAnother(final com.ferega.todo.model.User result) {
        this.URI = result.URI;

        this.username = result.username;
        this.passhash = result.passhash;
    }
    public User persist() throws java.io.IOException {
        final User result;
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
    public User delete() throws java.io.IOException {
        try {
            return _crudProxy.delete(User.class, URI).get();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }

    private String username;

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    public User setUsername(final String value) {
        if(value == null) throw new IllegalArgumentException("Property \"username\" cannot be null!");
        this.username = value;

        return this;
    }

    private String passhash;

    @JsonProperty("passhash")
    public String getPasshash() {
        return passhash;
    }

    public User setPasshash(final String value) {
        if(value == null) throw new IllegalArgumentException("Property \"passhash\" cannot be null!");
        this.passhash = value;

        return this;
    }

public static class auth implements java.io.Serializable, Specification<User> {
    public auth(
             final String username,
             final String passhash) {
        setUsername(username);
        setPasshash(passhash);
    }

    public auth() {
        this.username = "";
        this.passhash = "";
    }

    private static final long serialVersionUID = 0x0097000a;

    private String username;

    public String getUsername() {
        return username;
    }

    public auth setUsername(final String value) {
        if(value == null) throw new IllegalArgumentException("Property \"username\" cannot be null!");
        this.username = value;

        return this;
    }

    private String passhash;

    public String getPasshash() {
        return passhash;
    }

    public auth setPasshash(final String value) {
        if(value == null) throw new IllegalArgumentException("Property \"passhash\" cannot be null!");
        this.passhash = value;

        return this;
    }

        public java.util.List<User> search() throws java.io.IOException {
            return search(null, null, Bootstrap.getLocator());
        }
        public java.util.List<User> search(final ServiceLocator locator) throws java.io.IOException {
            return search(null, null, locator);
        }
        public java.util.List<User> search(final Integer limit, final Integer offset) throws java.io.IOException {
            return search(limit, offset, Bootstrap.getLocator());
        }
        public java.util.List<User> search(final Integer limit, final Integer offset, final ServiceLocator locator) throws java.io.IOException {
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
