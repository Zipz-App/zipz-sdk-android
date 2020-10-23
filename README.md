This open-source library allows you to integrate Zipz into your Android app.

# SDK Setup

All information needed to create the project of Zipz SDK implementation into your Android App.

## Android Studio Setup

To use the Zipz SDK in a project, add the SDK as a build dependency and import the SDK.

1.  Go to **Android Studio \| New Project \| Minimum SDK**.

2.  Select **API 21: Android 5.0 (Lollipop)** or higher and create your new project.

3.  After you create a new project, open Gradle Scripts \| build.gradle (Project: \<your_project\> and do the following:

    1.  Add the following to the buildscript { repositories {}} section of the build.gradle (Project) file:\

> mavenCentral{\'[[https://jitpack.io]{.ul}](https://jitpack.io)\'}

    2. Save and close build.gradle

4. Open Gradle Scripts \| build.gradle (Module: app) and do the following

   1. Add the following to the dependencies {} section of your build.gradle (module: app) file to compile the latest version of the Zipz SDK:

      `implementation 'com.github.Zipz-App:zipz-sdk-android:0.0.16`

      

   2. Save and close build.gradle (Module: app).



5.  Build your project. Now you can import our **sdk** into your app.



## Add Zipz App ID + App Secret

To make the SDK identifiable by Zipz servers, the Host App must add your Zipz App ID and App Secret to your project\'s strings file and update your Android manifest. *The Host App app_id and app_secret are available on the [Menu \> SDK Settings]{.ul} inside the Zipz SDK dashboard provided to the client*

1. Open your /app/res/values/strings.xml file.

   

2. Add a string element with the name attribute app_id and app_secret and value as your Zipz App ID and App Secret to the file.

> \<string name=\"**app_id**\"\>App ID\</string\>
>
> and
>
> \<string name=\"**app_secret**\"\>App secret\</string\>

3. Open /app/manifests/AndroidManifest.xml

4. Add a uses-permission element to the manifest: 

   `<uses-permission android:name="android.permission.INTERNET"/>`

# Request/Interaction

For all interactions between the Host App and Zipz SDK, the user requesting the data must be identified. Zipz provides a UUID for each user registered on its database.

## User registration

To register a new user and get a UUID for it, do the following:

1. The Host App must send to Zipz SDK the registration request with all data needed to register a new user.

   `LoginActivity.registrationUser(app_id, app_secret, email, first_name,Last_name, gender, date_of_birth, cpf, phone);`

   | parameter     | requirement  | data type                    | description                                                  |
   | ------------- | ------------ | ---------------------------- | ------------------------------------------------------------ |
   | app_id        | required     | string                       | public application unique identifier                         |
   | app_secret    | required     | string                       | secret application unique identifier                         |
   | email         | required     | string                       | must be a valid email address and unique in the database     |
   | first_name    | required     | string                       | must have at least 02 characters                             |
   | last_name     | required     | string                       | must have at least 02 characters                             |
   | gender        | required     | string (male, female, other) | must be one of the three options available                   |
   | date_of_birth | required     | string                       | valid date of birth in format yyyy-mm-dd. We accept only users older than 14 years old and younger than 100 years old |
   | cpf           | required     | string                       | must be a valid cpf number and unique in the database        |
   | phone         | not required | string                       | if present needs to be numeric format                        |

   

2\. The Host App must check the response from Zipz SDK to confirm if the user was created correctly or not. For checking, do the following:

Check the response code and message - methods for this information are `LoginActivity.checkMessage();` and `LoginActivity.checkRequestCode();`

When a new user registration is done with *success*, the response will contain the **code** 200 and the **message** success.

When a new user registration *fails*, the response will contain an *error* . (e.g. **code** 412 and **message** this email is already in the database or **code** 500 and **message** something went wrong ).

3\. If the registration was successfully done, the Host App is allowed to get the user\'s uuid and save on its own database. The user uuid will replace the user data in all future requests. For getting the user\'s **uuid**, do the following:

`AppUser appUser = LoginActivity.getUserInfo();` and `String appUUID = appUser.getUuid();`

**uuid** is in the format **rfc4122** (e.g 90eca5ae-c82b-4920-a7b2-c74bfe8c03cd) and must be saved to the Host App database.

## Init request

An initial request must happen every time a registered user opens the Host App. The Init request initializes Zipz SDK and establishes a secure connection between the Host App and the Zipz server. No other request can be made without Zipz SDK being initialized and if that happens, the response for all other requests will be code 401 message unauthorized. To initialize Zipz SDK, call next method:

`SDKActivity.initReq(app_id, app_secret, uuid);`

| parameter  | required | data type | description                                |
| ---------- | -------- | --------- | ------------------------------------------ |
| app_id     | required | string    | public application unique identifier       |
| app_secret | required | string    | secret application unique identifier       |
| uuid       | required | string    | universally unique identifier for the user |



## Cluster Request

Clusters are groups of venues defined by parameters to organize screens inside the Host App. To access a venue\'s cluster, the Host App must have the permission granted on its app_id. *All venue\'s clusters allowed to the Host App and its parameters are available on the [Menu \> SDK Settings]{.ul} inside the Zipz SDK dashboard provided to the client.*

##### **Venues Clusters List**

A list of all venues clusters allowed to the Host App containing information about all Clusters (VenuesCluster Object). To get the list, call next method:

`Cluster.venueClusterList();`

To access the response, call the next method:

`Cluster.getVenueClusterList();`

*Snippet code:*

`List<VenueCluster> dataSet = Cluster.getVenueClusterList();`

If the request fails, call the next method to check **ERRORS**;

`getMessageErrorVenueCluster();`

*Snippet code:*

`ErrorResponse errorResponse = getMessageErrorVenueCluster();`

All getters below are available inside the **VenueCluster Object** and they can be used by the Host App.

| **parameter**   | **data type** | description                                   |
| --------------- | ------------- | --------------------------------------------- |
| getUuid         | string        | universally unique identifier for the cluster |
| getType         | string        | shopping, brand, category and campaign        |
| getName         | string        | cluster name                                  |
| getAddress      | string        | cluster address (street name)                 |
| getStreetNumber | string        | cluster street number                         |
| getNeighborhood | string        | cluster neighborhood                          |
| getCity         | City          | cluster city                                  |
| getImage        | string        | cluster image-logo                            |
| getLatitude     | string        | cluster latitude                              |
| getLongitude    | string        | cluster longitude                             |
| getDescription  | string        | cluster description                           |

##### **Venue Cluster Details**

A venue cluster details contain information about the cluster itself **(VenueCluster Object)** and also information about all venues **(Venue Object)** assigned to that cluster. To get these objects, call the following request:

`VenueClusterDetailsFragment.getVenueClusterDetails(uuid)`

To access the response, call the next method:

`VenueClusterDetailsFragment.getVenueClusterDetailsList();`

*Snippet code:*

`List<Venue> venueListModels = VenueClusterDetailsFragment.getVenueClusterDetailsList();`

If the request fails, call the next method to check **ERRORS**;

`getMessageErrorVenueClusterDetails();`

*Snippet code:*

`ErrorResponse errorResponse = getMessageErrorVenueClusterDetails();`

The **Venue Cluster UUID** is required on this request. To check all getters available on **Venue Object**, go to the **Venues Request** section in the documentation.

Venues Request

Venues contain offers that are created directly by the merchant. The Host App has venues assigned to its app_id. All venues allowed to the Host App and its parameters are available on the Menu \> SDK Settings inside the Zipz SDK dashboard provided to the client.

##### 

##### **Venues List**

Venues contain offers that are created directly by the merchant. The host app has venues assigned to its APP_ID. All venues allowed to the host app and its parameters are available on the Menu \> SDK Settings inside the Zipz SDK dashboard provided to the client. To get list of venues call next method:

`Cluster.getVenueList();`

To access the response, call the next method:

`Cluster.getVenuesList();`

*Snippet code:*

`List<Venue> listOfVenues= Cluster.getVenuesList();`

If the request fails, call the next method to check **ERRORS**;

`getMessageErrorVenue();`

*Snippet code:*

`ErrorResponse errorResponse = getMessageErrorVenue();`

All getters below are available inside the **Venue Object** and they can be used by the Host App.

| **parameter**   | data type | description                                 |
| --------------- | --------- | ------------------------------------------- |
| getUuid         | string    | universally unique identifier for the venue |
| getName         | string    | venue name                                  |
| getName2        | string    | venue name2                                 |
| getDescription  | string    | venue description                           |
| getImage        | string    | venue image                                 |
| getCategory     | Category  | venue category                              |
| getAddress      | string    | venue street address                        |
| getStreetNumber | string    | venue street number                         |
| getNeighborhood | string    | venue neighborhood                          |
| getCity         | City      | venue city                                  |
| getState        | State     | venue state                                 |
| getLatitude     | string    | venue location                              |
| getLongitude    | string    | venue location                              |
| getHours        | Hours     | venue business hours                        |

##### **Venue Details**

A venue details contain all information about the venue itself and also the information from all offers assigned to that venue. Offers can be **public** or **private** and the merchant decides that when creating the coupon. Public Offers are available for all users, and private offers are for a specific audience based on the user behavior. To get these objects, do the following:

The **Venue UUID** is required on this request and Offer Object will also be responded. To check all getters available on Offer Objects, go to the Offer Request section in the documentation.

To get venue details call next method:

`VenueDetailsFragment.getVenueDetails(uuid);`

To access the response, call the next method:

`VenueDetailsFragment.getVenue();`

*Snippet code:*

`Venue venueObject = VenueDetailsFragment.getVenue();`

`List<PublicOffer> listOfPublicOffer = VenueDetailsFragment.getListOfPublicOffer();`

`List<PrivateOffer> listOfPrivateOffer = VenueDetailsFragment.getListOfPrivateOffer();`

If the request fails, call the next method to check **ERRORS**;

`getMessageErrorVenueDetails();`

*Snippet code:*

`ErrorResponse errorResponse = getMessageErrorVenueDetails();`

## Offer Request

Offers are coupons created by merchants and assigned to a specific venue. The Host App has venues assigned to its app_id. *All venues allowed to the Host App and its offers are available on the [Menu \> SDK Settings]{.ul} inside the Zipz SDK dashboard provided to the clients.*

##### **Offer details**

Offer details contain all information about the offer itself and the venue related to it. To get these objects, do the following:

The **Offer UUID** is required on this request and Offer Object will also be responded. To check all getters available on Offer Objects, go to the Offer Request section in the documentation.

Offer details request is to get an offer and venue object by **offer uuid** call next method.

`OfferActivity.offerDetailsRequest(uuid);`

To access the response, call the next method:

`OfferActivity.getOffer();`

Snippet code:

`Offer offer = OfferActivity.getOffer();`

If the request fails, call the next method to check **ERRORS**;

`getMessageErrorOfferDetails();`

*Snippet code:*

`ErrorResponse errorResponse = getMessageErrorOfferDetails();`

All getters below are available on the **Offer Object** and they can be used by the Host App.

| **parameter**       | data type | description                                 |
| ------------------- | --------- | ------------------------------------------- |
| getUuid             | string    | universally unique identifier for the offer |
| getName             | string    | offer's name                                |
| getDescription      | string    | offer's description                         |
| getImage            | string    | offer's image                               |
| getCategory         | Category  | offer's product category                    |
| getExpirationPeriod | integer   | offer's expiration period (in hours)        |
| getFullPrice        | string    | offer's regular price                       |
| getOfferPrice       | string    | offer's price                               |
| getQuantity         | string    | Available / Sold Out                        |
| getStatus           | string    | Active / Inactive                           |

## Transactions

Transactions are created when the user reserves an offer on the Offer Details screen. The Transaction starts with the offer reservation and it ends when the user redeems the offers scanning the QR code available at the venue.

**Reserve Offer**

Reserve Offer Request starts a Transaction and the Transaction Object will be responded. To get this object, do the following:

The **Offer UUID** is required on this request.

To make a request, call the next method:

`OfferActivity.reserveOfferRequest (uuid);`

To access the response, call the next method:

`OfferActivity.getReservedOffer();`

*Snippet code:*

`ReserveOffer reserveOffer = OfferActivity.getReservedOffer();`

If the request fails, call the next method to check **ERRORS**;

`getMessageErrorReservedOffer();`

*Snippet code:*

`ErrorResponse errorResponse = getMessageErrorReservedOffer();`

To check all getters available on Reserve Offer Object below.

| **parameter** | data type | description                            |
| ------------- | --------- | -------------------------------------- |
| getUuid       | String    | Id of transaction (Reserved Offer)     |
| getVenue      | Venue     | Venue Object which contains that Offer |
| getOffer      | Offer     | Offer which is reserved                |

**Transactions List**

Transactions List contains all transactions created. A transaction can have two status, Reserved and Redeemed. To get all past user transactions call the following method:

`TransactionFragment.transactionRequest();`

The full list of transactions containing all transactions (included all redeemed offers and reserved offers).

For accessing to all transactions, call the next method:

`TransactionFragment.getTransactionList();`

*Snippet code:*

`List<Transaction> transactionList = TransactionFragment.getTransactionList();`

For checking which transaction is redeemed or not redeemed check the status of the transaction inside the transaction object.

*Snippet code:*

`String status = transaction.getStatus();`

If the request fails, call the next method to check **ERRORS**;

`getMessageErrorTransaction();`

*Snippet code:*

`ErrorResponse errorResponse = getMessageErrorTransaction();`

All available getters for transaction object

| **parameter**     | data type | description                                       |
| ----------------- | --------- | ------------------------------------------------- |
| getUuid           | String    | Id of transaction                                 |
| getVenue          | Venue     | Venue Object which contains that Offer            |
| getOffer          | Offer     | Offer which is reserved                           |
| getStaff          | Staff     | Name of user who made transaction                 |
| getQrCard         | QrCard    | Name of qrCard                                    |
| getExpirationTime | String    | Expiration time of reserved offer                 |
| getRedeemedAt     | String    | Where offer is redeemed                           |
| getStatus         | String    | Status of transaction can be Redeemed or Reserved |



**Scanning QRcodeRedeem Offer**

## Redeem offer Transaction request

Before redeeming an offer, the user needs to reserve it first. When redeeming, the scanned QR code string and transaction uuid are sent as parameters in the following method:

`RedeemFragment.redeemTransaction(uuid, qrCode, expirationTime);`

To scan the QR code do the following steps:

Open CameraFragment:

`CameraFragment();`

*Snippet code:*

`CameraFragment cameraFragment = new CameraFragment();`

After scanning QR code, the CameraFragment will be closed and the QR code will be received in the method below:

`RedeemFragment.getQRCode();`

*Snippet code:*

`String qrCode = RedeemFragment.getQRCode();`

**NOTE: Zipz provides a QR scanner to Host App, but another one can be used if the Host App already has it. In this case,  our  CameraFragment() doesn't need to be opened. The Host App needs to attach the QR code on the request. If the Host App decides to use Zipz implementation for the QR scanner,  skip the 1st step below.**

After qrCode is provided, the user can redeem a reserved offer and get the correct response through the method below:

`RedeemFragment.redeemTransaction(uuid, qrCode, expirationTime);`

Response will be received calling the method below:

`RedeemFragment.getRedeemTransaction();`

*Snippet code:*

`RedeemTransaction redeemTransaction = RedeemFragment.getRedeemTransaction();`

**NOTE: the expirationTime to redeemTransaction is the time that the user has to redeem a reserved offer.**

To get expirationTime, check the Transaction request section.

*Snippet code:*

`String expirationTime = transaction.getExpirationTime();`

If the request fails, call the next method to check **ERRORS**;

`getMessageErrorRedeemOffer();`

*Snippet code:*

`ErrorResponse errorResponse = getMessageErrorRedeemOffer();`

All available getters and setters from RedeemTransaction

| **parameter**     | data type | description                                       |
| ----------------- | --------- | ------------------------------------------------- |
| getUuid           | String    | Id of transaction (redeemed offer)                |
| getVenue          | Venue     | Venue Object which contains that Offer            |
| getOffer          | Offer     | Offer which is redeemed                           |
| getStaff          | Staff     | Name of user who made redeemed                    |
| getQrCard         | QrCard    | Name of qrCard                                    |
| getExpirationTime | String    | Expiration time of reserved offer                 |
| getRedeemedAt     | String    | Where offer is redeemed                           |
| getStatus         | String    | Status of transaction can be Redeemed or Reserved |
