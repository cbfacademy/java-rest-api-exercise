# Java RESTful API Exercise: IOU Service

## Description

Build a RESTful API service that allows users to `create`, `read`, `update`, and `delete` IOUs (I Owe You) using Java and Spring Boot.

## Define the Data Model

Create an `IOU` entity class with fields like `id`, `borrower`, `lender`, `amount`, and `date`

```JSON
{
    "id": "",
    "borrower": "Bob",
    "lender": "Alice",
    "amount": 1877.51,
    "date": "2023-04-23T18:25:43.511Z"
}
```

## Implement RESTful Endpoints 

Create RESTful endpoints for the following operations:

1. Retrieve a list of all IOUs (HTTP GET)
1. Retrieve a specific IOU by ID (HTTP GET)
1. Create a new IOU (HTTP POST)
1. Update an existing IOU (HTTP PUT)
1. Delete an IOU by ID (HTTP DELETE)

| Method | URL | Description | 
|---|---|---|
| `GET` | `/ious`| Get all IOUs |  
| `GET` | `/ious/{id}`| Get an IOU by id |
| `POST` | `/ious` | Add an IOU |  
| `PUT` | `/ious/{id}` | Replace an IOU by Id |
| `DELETE` | `/ious/{id}` | Delete an IOU by id |

## Learning Objectives:

By the end of this exercise, you should be able to:

- Set up a Spring Boot project using a development environment
- Create a simple RESTful API for IOU tracking.
- Implement CRUD operations (Create, Read, Update, Delete) for IOUs

## Getting Started

### Clone the Repository
Clone this repository or or open in CodeSpaces.

```sh
git clone [REPO_URL]
cd [REPO_NAME]
```
Replace [REPO_URL] with the link to your GitHub repository and [REPO_NAME] with the repository's name.

### Install Dependencies

Open a terminal at the root of the repo directory and run the following command to install the dependencies:

```sh
./mvnw clean dependency:resolve
```

If you are on a Windows machine, that will be:
```cmd
mvnw clean dependency:resolve
```

#### 3. Running the Application

To start the API from the terminal, run the following command:

```sh
./mvnw spring-boot:run
```

Or on Windows:

```cmd
mvnw spring-boot:run
```