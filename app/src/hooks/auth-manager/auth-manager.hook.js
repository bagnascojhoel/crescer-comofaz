import { useEffect } from 'react';
import { useGlobalUser } from '../../context';
import { usePrivadoAutenticacaoApi } from '../privado-autenticacao-api/privado-autenticacao-api.hook';
import { useStorageManager } from '../storage-manager/storage-manager.hook';
import { useFacebookAuth } from './facebook-auth.hook';
import { useGoogleAuth } from './google-auth.hook';

export function useAuthManager() {
  const [globalUser, setGlobalUser] = useGlobalUser();
  const { keep, retrieve, clear } = useStorageManager();
  const { autenticarUsuario } = usePrivadoAutenticacaoApi(() => retrieve());

  const onLoginSuccess = (accessData, federation) => {
    if (accessData) {
      const user = { ...accessData, federation };

      setGlobalUser(user);
      keep(user);
    }
  };

  const onLogoutSuccess = () => {
    clear();
    setGlobalUser(null);
  };

  const onFailure = () => {};

  const { googleLogin } = useGoogleAuth({
    onLoginSuccess,
    onLogoutSuccess,
    onFailure,
  });

  const { facebookLogin } = useFacebookAuth({
    onFailure,
    onLoginSuccess,
    onLogoutSuccess,
  });

  const logout = async () => {
    if (globalUser.token) {
      onLogoutSuccess();
    }
  };

  const refreshSession = async () => {
    const storedUser = retrieve();
    const notExpired = storedUser && verifyNotExpired(storedUser.expiresAt);

    if (notExpired) {
      const response = await autenticarUsuario();
      if (response.idUsuario) {
        const user = retrieve();
        const accessData = user;
        const federation = user.federation;
        onLoginSuccess(accessData, federation);
        return;
      }
    }

    clear();
    setGlobalUser(null);
  };

  useEffect(() => {
    if (globalUser !== null) keep(globalUser);
  }, [globalUser]);

  return {
    googleLogin,
    facebookLogin,
    logout,
    refreshSession,
  };
}

function verifyNotExpired(expiresAt) {
  const now = new Date();
  const expiration = new Date(expiresAt);

  return expiration > now;
}
