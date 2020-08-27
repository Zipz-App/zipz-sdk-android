# Zipz SDK for Android

This open-source library allows you to integrate Zipz into your Android app.



## SDK Setup

All information needed to create the project of Zipz SDK implementation into your Android App.



### Android Studio Setup

To use the Zipz SDK in a project, add the SDK as a build dependency and import the SDK.

1. Go to **Android Studio | New Project | Minimum SDK**.

   

2. Select **API 21: Android 5.0 (Lollipop)** or higher and create your new project.

   

3. After you create a new project, open `Gradle Scripts | build.gradle (Project: <your_project>` and do the following:

   

   1. Add the following to the `buildscript { repositories {}}` section of the `build.gradle (Project)` file: 

      **mavenCentral**{'https://jitpack.io'}

      

   

   2. Save and close `build.gradle (Project: <your_project>)`.



4. Open `Gradle Scripts | build.gradle (Module: app)` and do the following:

   

   1. Add the following to the `dependencies {}` section of your `build.gradle (module: app)` file to compile the latest version of the Zipz SDK:

      

      ```
      implementation 'com.github.Zipz-App:zipz-sdk-android:0.0.13'
      ```

      

   2. Save and close `build.gradle (Module: app)`s

   

5. Build your project. Now you can import ou **SDK** into your app.



### Add Zipz App ID + App Secret



To make the SDK identifiable by Zipz servers, the Host App must add your Zipz App ID and App Secret to your project's strings file and update your Android manifest.  *The Host App app_id and app_secret are available on the <u>Menu > SDK Settings</u> inside the Zipz SDK dashboard provided to the client*



1. Open your `/app/res/values/strings.xml` file.

   

2. Add a `string` element with the name attribute `app_id`  and `app_secret`, and value as your App ID and App Secret to the file. 

```code
<string name="app_id">App ID</string>
```

and 

```
<string name="app_secret">App secret</string>
```



3. Open `/app/manifests/AndroidManifest.xml`

   

4. Add a `uses-permission` element to the manifest:

```code
<uses-permission android:name="android.permission.INTERNET"/>
```





## Requests/Interactions

For all interactions between the Host App and Zipz SDK, the user requesting the data must be identified. Zipz provides a UUID for each user registered on its database. 



### User registration

To register a new user and get a UUID for it, do the following:



1. The Host App must send to Zipz SDK the registration request with all data needed to register a new user.

   `LoginActivity.registrationUser(app_id, app_secret, email, first_name, last_name, gender, date_of_birth, cpf, phone);`

​	

| parameter     | requirement  | data type                    | description                                                  |
| ------------- | ------------ | ---------------------------- | ------------------------------------------------------------ |
| app_id        | required     | int                          | public application unique identifier                         |
| app_secret    | required     | string                       | secret application unique identifier                         |
| email         | required     | string                       | must be a valid email address and unique in the database     |
| first_name    | required     | string                       | must have at least 02 characters                             |
| last_name     | required     | string                       | must have at least 02 characters                             |
| gender        | required     | string (male, female, other) | must be one of the three options available                   |
| date_of_birth | required     | date                         | valid date of birth in format yyyy-mm-dd. We accept only users older than 14 years old and younger than 100 years old |
| cpf           | required     | int                          | must be a valid cpf number and unique in the database        |
| phone         | not required | string                       | if sent it must be a numeric format                          |



2. The Host App must check the response from Zipz SDK to confirm if the user was created correctly or not. For checking, do the following:

   

   Check the response code and message - methods for this information are  `LoginActivity.*checkMessage*();` and `LoginActivity.*checkRequestCode*();`

   

   When a new user registration is done with *success*,  the response will contain  the **code** `200`  and the **message** `success`. 

   

   When new user registration *fails*, the response will contain an *error* . (e.g. **code** `412` and **message** `this email is already in the database` or **code** `500` and **message** `something went wrong` ).

   

3. If the registration was successfully done, the Host App is allowed to get the user's uuid and save on its own database. The user uuid will replace the user data in all future requests. For getting the user's uuid, do the following: 

   

   `AppUser appUser = LoginActivity.*getUserInfo*();` and `String appUUID = appUser.getUuid();`

   

   **uuid** is in the format **rfc4122** (e.g 90eca5ae-c82b-4920-a7b2-c74bfe8c03cd) and must be saved to the Host App database.





### Init request

An initial request must happen every time a registered user opens the Host App. The Init request initializes Zipz SDK and establishes a secure connection between the Host App and the Zipz server. No other request can be made without Zipz SDK being initialized and if that happens, the response for all other requests will be **code** `401` **message** `unauthorized`. To initialize Zipz SDK, do the following:



 `SDKActivity.*initReq*(app_id, app_secret, uuid);`



| parameter  | requirement | data type | description                                    |
| ---------- | ----------- | --------- | ---------------------------------------------- |
| app_ip     | required    | int       | public application unique identifier           |
| app_secret | required    | string    | secret application unique identifier           |
| uuid       | required    | string    | the universally unique identifier for the user |



### Clusters request



Clusters are groups of venues defined by parameters to organize screens inside the Host App. To access a venue's cluster, the Host App must have the permission granted on its app_id. *All venue's clusters allowed to the Host App and its parameters are available on the <u>Menu > SDK Settings</u> inside the Zipz SDK dashboard provided to the client.*



##### Venues Clusters List

A list of all venues clusters allowed to the Host App containing information about all Clusters (VenuesCluster Object). To get the list, do the following:



`Cluster.venueClusterList();List<VenueCluster> dataSet = `

`Cluster.*getVenueClusterList*();`



All getters below are available inside the **VenueCluster Object** and they can be used by the Host App.



| parameter       | data type | description                                   |
| --------------- | --------- | --------------------------------------------- |
| getType         | string    | shopping, brand, category and campaign        |
| getUuid         | string    | universally unique identifier for the cluster |
| getName         | string    | cluster name                                  |
| getAddress      | string    | cluster address (street name)                 |
| getStreetNumber | string    | cluster street number                         |
| getCity         |           | cluster city                                  |
| getImage        |           | cluster image-logo                            |
| getLatitude     |           | cluster latitude                              |
| getLongitude    |           | cluster longitude                             |
| getNeighborhood |           | cluster neighborhood                          |
| getDescription  |           | cluster description                           |



**ERROR MESSAGES:** under development



##### Venue Cluster Details

A venue cluster details contain information about the cluster itself **(VenueCluster Object)** and also information about all venues **(Venue Object)** assigned to that cluster. To get these objects, do the following:



The **Venue Cluster UUID** is required on this request. To check all getters available on **Venue Object**, go to the **Venues Request** section in the documentation.



`List<Venue> venueListModels = `

`VenueClusterDetailsFragment.getVenueClusterDetails(uuid)*VenueClusterDetailsFragment.getVenue*();`



**ERROR MESSAGES:** under development





### Venues request



Venues contain offers that are created directly by the merchant. The Host App has venues assigned to its app_id. All venues allowed to the Host App and its parameters are available on the Menu > SDK Settings inside the Zipz SDK dashboard provided to the client.



##### Venues list

A list of all venues allowed to the Host App containing information about all Venues (Venues Object). To get the list, do the following:



`Cluster.getVenueList();`
`List<Venue> listOfVenues= Cluster.getVenueList();`



All getters below are available inside the **Venue Object** and they can be used by the Host App.



| parameters      | data type | description |
| --------------- | --------- | ----------- |
| getUuid         |           |             |
| getName         |           |             |
| getName2        |           |             |
| getDescription  |           |             |
| getImage        |           |             |
| getCategory     |           |             |
| getAddress      |           |             |
| getStreetNumber |           |             |
| getNeighborhood |           |             |
| getCity         |           |             |
| getState        |           |             |
| getHour         |           |             |
| getLatitude     |           |             |
| getLongitude    |           |             |



**ERROR MESSAGES:** under development



##### Venue details

A venue details contain all information about the venue itself and also the information from all offers assigned to that venue. Offers can be **public** or **private** and the merchant decides that when creating the coupon. Public Offers are available for all users, and private offers are for a specific audience based on the user behavior. To get these objects, do the following:

The **Venue UUID** is required on this request.  To check all getters available on **Offer Object**, go to the **Offer Request** section in the documentation.



`VenueDetailsFragment.*getVenueDetails*(uuid);`

`Venue venueObject = VenueDetailsFragment.*getVenue*();`

`List<PublicOffer> listOfPublicOffer = VenueDetailsFragment.*getListOfPublicOffer*();`

`List<PrivateOffer> listOfPrivateOffer = VenueDetailsFragment.*getListOfPrivateOffer*();`



**ERROR MESSAGES:** under development



### Offers request

Offers are coupons created by merchants and assigned to a specific venue. The Host App has venues assigned to its app_id. *All venues allowed to the Host App and its offers are available on the* *Menu > SDK Settings* *inside the Zipz SDK dashboard provided to the clients.*



##### Offer Details



Offer details contain all information about the offer itself and the venue related to it. To get these objects, do the following:



The **Offer UUID** is required on this request. 



`OfferActivity.*offerDetailsRequest*(uuid);`
`Offer offer = OfferActivity.*getOffer*();`



All getters below are available on the **Offer Object** and they can be used by the Host App.



| parameters          | data type | description                                     |
| ------------------- | --------- | ----------------------------------------------- |
| getUuid             |           | the universally unique identifier for the offer |
| getName             |           | offer's name                                    |
| getDescription      |           | offer's description                             |
| getImage            |           | offer's image                                   |
| getCategory         |           | offer's categories                              |
| getExpirationPeriod |           | Coupon expiration period (in hours)             |
| getFullPrice        |           | Regular price                                   |
| getOfferPrice       |           | Offer's price                                   |
| getQuantity         |           | Available / Sold-out                            |
| getStatus           |           | Active / Inactive                               |







### Transactions request
