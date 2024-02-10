# Public APIs Listing Application

This Spring Boot application provides APIs to interact with the Public APIs listing service provided by [https://api.publicapis.org/](https://api.publicapis.org/).

## Introduction

This application consists of two main tasks:

1. **List Entries by Category**: Given a category, the application retrieves entries from the Public APIs listing based on that category.

2. **Save New Entry**: Allows users to save a new entry with relevant properties to the Public APIs listing.

## Getting Started

### Prerequisites

To run this application, you need:

- Java Development Kit (JDK) 8 or higher
- Maven
- MySQL Server


## Usage

### Task 1: List Entries by Category

To list entries by category, send a GET request to the `/api/list` endpoint with the `category` parameter.

Select the "Params" tab in Postman.
In the key-value pairs, add a new entry with the key set to category and the value set to the desired category string.

Example using URL: 'http://localhost:8080/api/list?category=Books'

Response will be a JSON array containing entries matching the specified category.

### Task 2: Save New Entry

To save a new entry, send a POST request to the `/api/save` endpoint with a JSON payload containing the new entry properties (`API`, `Description`, `Category`, etc.).

Example using URL: 'http://localhost:8085/api/save'
```json
{
  "Title": "AniAPI",
  "Description": "Anime discovery, streaming & syncing with trackers",
  "Category": "Anime"
}
```


Response will indicate whether the entry was saved successfully.