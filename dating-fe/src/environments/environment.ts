// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080',
  firebaseConfig : {
    apiKey: "AIzaSyCSJISWYxmQfje90tniQeDBcRI9C5C2dz0",
    authDomain: "chat-room-bda6f.firebaseapp.com",
    databaseURL: "https://chat-room-bda6f-default-rtdb.firebaseio.com",
    projectId: "chat-room-bda6f",
    storageBucket: "chat-room-bda6f.appspot.com",
    messagingSenderId: "177397455932",
    appId: "1:177397455932:web:13347c58fc2540481a3739"
  }
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
