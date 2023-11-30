# SpringBootRestApi

Brief project description.

## Table of Contents

- [Endpoints](#endpoints)
  - [Users](#users)
  - [Products](#products)
  - [Favourite Products](#favourite-products)
  - [External API](#external-api)


## Endpoints

### Users

- **Register User**
  - Method: POST
  - Endpoint: `/api/users/register`

- **Login User**
  - Method: POST
  - Endpoint: `/api/users/login`

- **Get All Users**
  - Method: GET
  - Endpoint: `/api/users/allUsers`

- **Get User by ID**
  - Method: GET
  - Endpoint: `/api/users/user/{userId}`

- **Update User by ID**
  - Method: PUT
  - Endpoint: `/api/users/updateUser/{userId}`

- **Delete User by ID**
  - Method: DELETE
  - Endpoint: `/api/users/deleteUser/{userId}`

### Products

- **Get All Products**
  - Method: GET
  - Endpoint: `/produits`

- **Add Product**
  - Method: POST
  - Endpoint: `/addProduit`

- **Display Product by ID**
  - Method: GET
  - Endpoint: `/displayProduct/{id}`

- **Delete Product by ID**
  - Method: DELETE
  - Endpoint: `/deleteProduct/{id}`

- **Get Product by Name**
  - Method: GET
  - Endpoint: `/product/{name}`

- **Get Product by the Highest Price**
  - Method: GET
  - Endpoint: `/productByTheHighestPrice/{price}`

- **Update Product by ID**
  - Method: PUT
  - Endpoint: `/productUpdate/{id}`

### Favourite Products

- **Add Favorite Product for User**
  - Method: POST
  - Endpoint: `/api/favouriteProduct/addFavoriteProduct/{userId}/{productId}`

- **Get User's Favorite Products**
  - Method: GET
  - Endpoint: `/api/favouriteProduct/userFavoriteProducts/{userId}`

- **Get All Users with Favorites**
  - Method: GET
  - Endpoint: `/api/favouriteProduct/allUsersWithFavorites`

### External API

- **Transcribe**
  - Method: POST
  - Endpoint: `/transcribe`

- **Get All Transcriptions**
  - Method: GET
  - Endpoint: `/transcriptions`

- **Get Transcription by ID**
  - Method: GET
  - Endpoint: `/transcript/{transcriptionId}`



Specify the license under which your project is distributed.

