import {
  useGoogleLogin as _useGoogleLogin,
  useGoogleLogout,
} from 'react-google-login';
import FEDERATIONS from './federations';

const clientId = '366427002792-1t6h9cr7u6mfnm8f07oqodfl0o15iklf.apps.googleusercontent.com';

export function useGoogleAuth({ onLoginSuccess, onLogoutSuccess, onFailure }) {
  const onSuccess = (res) => {
    const { googleId, tokenId, tokenObj } = res;

    onLoginSuccess(
      { userId: googleId, token: tokenId, expiresAt: tokenObj.expires_at },
      FEDERATIONS.GOOGLE
    );
  };

  const { signIn, loaded: isSignInLoaded } = _useGoogleLogin({
    onSuccess,
    onFailure,
    clientId,
    isSignedIn: false,
  });

  const { signOut, loaded: isSignOutLoaded } = useGoogleLogout({
    clientId,
    onLogoutSuccess,
    onFailure,
  });

  const googleLogin = () => isSignInLoaded && signIn();

  const googleLogout = () => isSignOutLoaded && signOut();

  return {
    googleLogin,
    googleLogout,
  };
}
