package com.ferega.todo.model.repositories;

import com.dslplatform.patterns.*;
import com.dslplatform.client.*;

public class UserRepository extends ClientPersistableRepository<com.ferega.todo.model.User> {
    public UserRepository(final ServiceLocator locator) {
        super(com.ferega.todo.model.User.class, locator);
    }
}
