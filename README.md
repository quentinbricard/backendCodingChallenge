#Backend Coding Challenge

## How to deploy the application

* You can directly launch the embedded container (Tomcat) with either:
`gradle bootrun` or `./gradlew bootrun`

* You can also package the application as a war and deploy it manually to a standalone Tomcat instance.

## Features

This application ties to the following end-points:
* Subscription Create: <URL>/subscriptions/create?eventUrl={eventUrl}
* Subscription Change: <URL>/subscriptions/change?eventUrl={eventUrl}
* Subscription Cancel: <URL>/subscriptions/cancel?eventUrl={eventUrl}
* Subscription Status: <URL>/subscriptions/status?eventUrl={eventUrl}

* User Assignment: <URL>/user/assignment?eventUrl={eventUrl}
* User Unassignment: <URL>/user/unassignment?eventUrl={eventUrl}

## Steps to use the application

* Due to security purpose, the application doesn't work on localhost.
* To bypass this issue, I used [ngrok](https://ngrok.com/) which enables tunneling to localhost.
* You can run it with:\n
`./ngrok http 8080`
* Then the resulting URL should be displayed like:\n
`Forwarding                    http://179d8139.ngrok.io -> localhost:8080`
* The URL is the one which needs to be used in integration settings


## Security

* Oauth 1.0 is used to handle requests
* File `oauth.properties` contains the OAuth configuration from integration settings page

## Error handling

* If OAuth request validation fails (no authorization header, wrong key, etc), a 403 error is returned (Forbidden)
* If other verb than GET is used to handle requests, 405 status is returned (METHOD_NOT_ALLOWED)
* Every other requests return a 200 status (OK) with a success flag and an errorcode (if applicable), as asked in specifications
* Here are the different error codes handled by this application:\n
*- ACCOUNT\_NOT\_FOUND: returned when no account is found upon the accountIdentifier sent by Appdirect\n
*- INVALID\_RESPONSE: returned when an error occurs during Appdirect response parsing\n
*- TRANSPORT\_ERROR: returned when an error occurs during request signature\n
*- TRANSPORT\_ERROR: returned when an error occurs during request signature\n
*- FORBIDDEN: returned when an endpoint is called with eventUrl parameter\n
*- UNKNOWN\_ERROR: returned when an unexpected error occurs\n

## Technical implementation

* This project has been built with *Spring Boot*
* A in-memory database *H2* is used to store data
* *Hibernate* is the default vendor
* OAuth requests validation is done with *Spring*
* *OAuth signpost* is used to sign outgoing connections to Appdirect marketplace
* *Mockito* and *PowerMock* are used for unit testing purposes

## Limitations

* Not all information received by Appdirect is stored in database
* In particular, the creator of a subscription is not saved.\n
As a result, when assigning a user, the application doesn't check if the user is different from the creator.
* When receiving responses from Appdirect, there is no validation on the unique identifier of the event
* These endpoints have not been implemented:\n
*- Change subscription
*- User Update
