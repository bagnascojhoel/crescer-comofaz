import FEDERATIONS from './federations';

export function useFacebookAuth({
  onLoginSuccess: _onLoginSuccess,
  onLogoutSuccess,
  onFailure,
}) {
  const onLoginSuccess = (res) => {
    if (res) {
      const { accessToken, userID, expiresIn } = res;
      const expiresAt = new Date().getTime() + expiresIn;

      _onLoginSuccess(
        { token: accessToken, userId: userID, expiresAt },
        FEDERATIONS.FACEBOOK
      );
    }
  };

  const facebookLogin = async () => {
    try {
      window.FB.login((response) => onLoginSuccess(response?.authResponse), {
        scope: 'public_profile,email',
      });
    } catch (error) {
      onFailure(error);
    }
  };

  const facebookLogout = async () => {
    try {
      await new Promise(window.FB.logout);
      onLogoutSuccess();
    } catch (error) {
      onFailure(error);
    }
  };

  return {
    facebookLogin,
    facebookLogout,
  };
}
