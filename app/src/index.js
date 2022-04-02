import './index.css';
import React from 'react';
import ReactDOM from 'react-dom';
import App from './App.jsx';
import { BrowserRouter } from 'react-router-dom';
import {
  ToastMessageProvider,
  UserProvider,
  AuthRequiredMessageProvider,
  ApiErrorMessageProvider,
} from './context';
import { initFacebookSdk } from './helpers/init-facebook-sdk';

initFacebookSdk().then(initApp);

function initApp() {
  ReactDOM.render(
    <BrowserRouter>
      <UserProvider>
        <ToastMessageProvider>
          <AuthRequiredMessageProvider>
            <ApiErrorMessageProvider>
              <App />
            </ApiErrorMessageProvider>
          </AuthRequiredMessageProvider>
        </ToastMessageProvider>
      </UserProvider>
    </BrowserRouter>,
    document.getElementById('root')
  );
}
