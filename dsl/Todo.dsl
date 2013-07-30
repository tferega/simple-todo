module model
{
  root Task
  {
    User *user;

    String name;
    String description;
    Int? priority;

    specification findByUser 'task => task.userID == username'
    {
      String username;
    }
  }

  root User(username)
  {
    String username;
    Binary salt;
    Binary passhash;
  }
}