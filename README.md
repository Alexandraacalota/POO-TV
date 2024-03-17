Copyright 2023 Alexandra-Maria Calota
# POO TV

## Overview
The project represents the implementation of the backend for a movies platform.

## Project Details
- I created a database using the Singleton pattern in order to store all the registered users and the movies available on the platform.
- The `currentUserData` is also created using the Singleton pattern and stores information about the current page: the user that has logged in and the movies that are displayed on the screen at that specific moment.
- The available pages are created as subclasses of the `Page` class, which owns methods meant to be overridden. They are boolean methods which have two different purposes: to either check if it is possible to navigate from one type of page to another or to check if the current page is of a specific type.
- The actions are stored in an `ArrayList` and then, depending on their type, calls one of the two methods: `onPage` or `changePage`.
- The change page actions are implemented using the Factory design pattern, which creates a new instance of the class that represents the required page. It contains a switch case based on the names of the pages the user wants to navigate through and returns either a new page, or the old one in case of an error.

## Available pages
- Unauthenticated Homepage
- Login
- Register
- Authenticated Homepage
- Movies
- See details
- Upgrades
- Logout

## Available actions
  - **login:** on the Login Page - the action through which a user signs in to his platform account.
  - **register:** on the Register Page - the action that adds a user to the platform's database.
  - **search:** on the Movies Page - action that shows all movies starting with a given string.
  - **filter:** on the Movies Page - action which only displays the movies that match the details that the user is looking for: actors and genre. They can also be sorted in ascending or descending order by duration and rating.
  - **purchase:** on the See details Page - action after which a movie is added to the user's purchased movies list.
  - **watch:** on the See details Page - only in case of a successful purchase, this action adds the movie to the user's watched movies list.
  - **like:** on the See details Page - only in case of a successfully watched movie, this action increases the number of likes the movie has and adds it to the current user's watched movies list.
  - **rate the movie:** on the See details Page - only in case of a successfully watched movie being displayed on screen, this action changes the movie's rate in the database and then adds it to the current user's rated movies list.
  - **buy premium account:** on the Upgrades Page - action that decreases the current user's balance and changes his account type from standard to premium.
  - **buy tokens:** on the Upgrades Page - action that decreases the current user's balance by the number of tokens he wants to buy and adds them to his tokens count.
  - **logout:** on the Logout Page - clears all the current data stored and switches back to the Unauthenticated Homepage.
  - **subscribe:** on the See Details Page - adds one of the current movie's genres to the current user's subscriptions.
  - **add:** adds a new movie to the database.
  - **delete:** deletes a movie from the database.
  - **back:** returns to the previous page the user navigated through.

- Whenever a user wants to buy a movie, the purchase method is created using the Strategy Design Pattern. It has one method that is overridden in the MoviePurchase and FreeMovie classes, which decrement either the user's tokens count or his free premium movies.
- After each new movie added to the database, all users subscribed to at least one of its genres receive a notification.
- After each movie deleted from the database, all users that have previously bought it receive their tokens or free movie back, based on their account type and a notification that lets them know the movie has been deleted.
- I used the Observer design pattern for the notifications received by the users after each added movie. The users implement the Observer interface that only contains an update method, which is overridden in the User class.
- The Observable class contains a method attach that adds genres and users subscribed to them inside a hashmap (with genres as keys and ArrayLists of users as values) and a method notifyAllObservers that calls the update method from the User class and sends the notification to all users matching the required criteria.
- The last user logged in at the end of the actions list, if premium, receives a recommendation based on his most liked genres, and, in case of equality, the first one in the movies database that has the most likes and respects the genre criteria.
- The Output class contains a generalOutput method that is used to add the output to every command to the ArrayNode that will then be printed to the json file.