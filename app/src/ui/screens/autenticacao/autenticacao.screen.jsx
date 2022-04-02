import './styles.css';
import React, { useEffect } from 'react';
import { useHistory } from 'react-router-dom';
import { MainButton, Container, FederatedLoginButton } from '../../components';
import facebookLogo from '../../../assets/facebook-logo.svg';
import googleLogo from '../../../assets/google-logo.svg';
import {
  useApiErrorMessageManager,
  useAuthManager,
  usePrivadoAutenticacaoApi,
  useToastMessageManager,
} from '../../../hooks';
import { ROUTES } from '../../../routes';
import { COLORS } from '../../../styles';
import { useGlobalUser } from '../../../context';

export function AutenticacaoScreen() {
  const { push } = useHistory();
  const apiErrorMessage = useApiErrorMessageManager();
  const toastMessage = useToastMessageManager();
  const { facebookLogin, googleLogin } = useAuthManager();
  const { autenticarUsuario } = usePrivadoAutenticacaoApi();

  const [globalUser, setGlobalUser] = useGlobalUser();

  const handleFacebookLogin = () => facebookLogin();

  const handleGoogleLogin = () => googleLogin();

  useEffect(() => {
    (async () => {
      if (globalUser) {
        const response = await autenticarUsuario();

        if (response.error) apiErrorMessage.send(response.error);
        else {
          toastMessage.send('Logado com sucesso');

          setGlobalUser({
            ...globalUser,
            idUsuario: response.idUsuario,
            primeiroAcesso: response.primeiroAcesso,
          });

          if (response.primeiroAcesso) push(ROUTES.EDITAR_INTERESSES);
          else push(ROUTES.HOME);
        }
      }
    })();
  }, [globalUser]);

  return (
    <Container column className="autenticacao-screen__container">
      <AutenticacaoBackgroundImage />
      <AutenticacaoMainContent />

      <div className="autenticacao-screen__login-options">
        <FederatedLoginButton
          onClick={handleFacebookLogin}
          imageUrl={facebookLogo}
          text="entrar com facebook"
          textColor={COLORS.FACEBOOK_BLUE}
        />
        <FederatedLoginButton
          onClick={handleGoogleLogin}
          imageUrl={googleLogo}
          text="entrar com google"
          textColor={COLORS.GOOGLE_RED}
        />
      </div>
      <VisitorAction />
    </Container>
  );
}

function AutenticacaoMainContent() {
  return (
    <div className="autenticacao-screen__main-content --flex-center --flex-column">
      <img
        className="autenticacao-screen__logo"
        src="/logo-texto-branco.svg"
        alt="ComoFaz logo"
      />
      <p>
        Alimente seu espírito
        <br /> DIY todos os dias
      </p>
    </div>
  );
}

function AutenticacaoBackgroundImage() {
  return <div className="autenticacao-screen__background"></div>;
}

function VisitorAction() {
  const { push } = useHistory();

  const handleClick = () => {
    push(ROUTES.HOME);
  };

  return (
    <MainButton
      className="autenticacao-screen__login-visitor"
      onClick={handleClick}
    >
      Entrar como visitante
    </MainButton>
  );
}
