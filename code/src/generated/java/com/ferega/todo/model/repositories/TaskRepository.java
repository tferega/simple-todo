package com.ferega.todo.model.repositories;

import com.dslplatform.patterns.*;
import com.dslplatform.client.*;

public class TaskRepository extends ClientPersistableRepository<com.ferega.todo.model.Task> {
    public TaskRepository(final ServiceLocator locator) {
        super(com.ferega.todo.model.Task.class, locator);
    }
}
