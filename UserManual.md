```
,--.--------.   _,.---._                   _,.---._     
/==/,  -   , -\,-.' , -  `.   _,..---._   ,-.' , -  `.   
\==\.-.  - ,-./==/_,  ,  - \/==/,   -  \ /==/_,  ,  - \  
 `--`\==\- \ |==|   .=.     |==|   _   _\==|   .=.     | 
      \==\_ \|==|_ : ;=:  - |==|  .=.   |==|_ : ;=:  - | 
      |==|- ||==| , '='     |==|,|   | -|==| , '='     | 
      |==|, | \==\ -    ,_ /|==|  '='   /\==\ -    ,_ /  
      /==/ -/  '.='. -   .' |==|-,   _`/  '.='. -   .'   
      `--`--`    `--`--''   `-.`.____.'     `--`--''

```

# ToDo Application User Manual


## About ToDo

This application was developed by [Nino Prekratić](https://www.linkedin.com/in/ninoprek/) in `Java` programming language. It was and individual project in a [Novare Potential Software Development Academy 3](https://www.novarepotential.com/software-development-academy-eng/). 

The purpose of this application is to have a list of tasks (a to do list) which serve as a reminder for a user. A single user can have multiple projects that can hold multiple tasks. Every task is described by the title, due date and completion status. Projects are described by their names. Projects related to a user can be saved to a file, and loaded when the application is runed again. More details about functionalities and available commands of the application can be seen in the *Using ToDo Application* section.

## Installing and running the ToDo application

In order to run the application, it has to be compiled first. It is necessary to use `Java 8` or higher. Also, it is recommended to use [IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=mac) for compiling and running, since the application was developed in this `IDE`. 

## Using ToDo application

Interaction with the application is performed through a text-based user interface. Some of the commands are two words commands. 

Available commands and their functions are:

- `help` - Lists all availabe commands.
- `new`  - Starts the creation of a new user, new project and first task.
    - `new task` - Creates a new task for a current user in a current project. 
    - `new project` - Creates a new project for a current user and sets it as a current project.
    - `new user` - Creates a new user and sets it as a current user.
    
- `user` - Shows who is the current user.
- `project` - shows the current project.
- `change` - Two word command and it is used for changeing current project or current user.
    - `change project` - Changes the current project of the current user. The project is selected by entering valid project number after all projects are listed. 
    - `change user` - Chnages the current user. The user is selected by entering valid user number after all users are listed.
    
- `tasks` - Lists all tasks in a current project for a current user sorted by date (closest to farthest).
    - `tasks finished` - Lists all tasks in current project for a current user that have their status set as *finished*.
    - `tasks unfinished` - Lists all tasks in current project for a current user that have their status set as *unfinished*.
    
- `projects` - Lists all projects for a current user.
- `edit` - Two word command and is used to edit task elements or project name for a current user.
    - `edit task` - Edit's task elements title, due date or status. Task is selected by entering a valid task number after all tasks for a current project are listed. Task elements are specified by words *title*, *date* or *status* after the task is selected.
    - `edit project` - Edit's project's name. Project is selected by entering a valid project number after all project for a current user are listed. 

- `remove` - Two word command than can remove a task or a project for a current user. 
    - `remove task` - Removes a task that is selected by entering a valid task number after all tasks in current project are listed.
    - `remove project` - Removes a project that is selected by entering a valid project number after all projects are listed.

- `save` - Save user data to a file. User is selected by entering a valid user number after all available users are listed.

- `load` - Loads the user data from the file. User is selected by entering a valid user number after all available users are listed.
- `quit` - Quit's the application.


## Note

Thank you for trying the *ToDo* application! Feel free to [contact me](ninoprekratic@gmail.com) if you have some questiones, report bugs or want to collaborate.


