

# POO TV - Etapa 1

## Vijaeac Rares
### 322CD
### December 2022



## Structure

* src/
  * actions/
    * LoginPage - the class used to change page to login and to log on the platform
    * RegisterPage - changes the page to register and registers the users in the platform's database
    * Logout - removes the user from the authenticated page
    * MoviePage - changes the page to movie, creates the list of movies and here you can filter and search movies
    * SeeDetailsPage - shows the details of the given movie, here you can purchase the movie, watch it, like it and rate it
    * UpgradePage - here you can change your account from standard to premium and buy tokens, the platform's currency
    * PrintOutput - prints the output in JSON format
    * Constants - contains the constants used in the implementation, such as the number of free premium movies
    * Page - the interface used for every page that has the method change page
  * database/ - contains the Database class, which saves the current user, current movies list and if the user is authenticated or not, which I implemented using the Singleton pattern in order to access it from every class since they are constantly changed

* main/ - contains the entry point of the implementation
* input/ - classes received from the input in JSON format
* ref/ - contains all reference output for the tests in JSON format
* implementation/ - executes the given actions from the input
