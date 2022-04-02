import React, { useEffect, useState } from 'react';
import { ROUTES } from './routes';
import { Redirect, Route, Switch, useHistory } from 'react-router-dom';
import {
  AutenticacaoScreen,
  CadastrarPostScreen,
  EditarInteressesScreen,
  ExibirPostScreen,
  FiltragemScreen,
  HomeScreen,
  FavoritosScreen,
  PerfilScreen,
  EditarPerfilScreen,
  FeitosScreen,
  SuasListasScreen,
  FiltragemResultadosScreen,
  BuscaScreen,
  ExibirComentariosScreen,
  NaoEncontradoScreen,
  ExibirListaScreen,
  AdicionarPostListaScreen,
  EditarPostScreen,
} from './ui/screens';
import {
  Loader,
  PageHeader,
  ToastMessage,
  AuthRequiredMessage,
  ApiErrorMessage,
} from './ui/components';
import { PageHeaderGoBack } from './ui/components/page-header-go-back/page-header-go-back.component';
import {
  useApiErrorMessageManager,
  useAuthManager,
  usePublicoStatusCheckApi,
} from './hooks';
import { useGlobalUser } from './context';

export default function App() {
  const apiErrorMessage = useApiErrorMessageManager();
  const { checkStatus } = usePublicoStatusCheckApi();
  const { refreshSession } = useAuthManager();

  const [refreshed, setRefreshed] = useState(false);

  useEffect(() => {
    (async () => {
      const response = await checkStatus();

      if (response.error) apiErrorMessage.send(response.error);

      await refreshSession();
      setRefreshed(true);
    })();
  }, []);

  if (!refreshed) return <Loader fullScreen large />;

  return (
    <>
      <Switch>
        <PublicRoute path={ROUTES.AUTENTICACAO} screen={AutenticacaoScreen} />

        <PrivateRoute
          path={`${ROUTES.EDITAR_INTERESSES}/:selected`}
          screen={EditarInteressesScreen}
        />

        <PrivateRoute
          path={ROUTES.EDITAR_INTERESSES}
          screen={EditarInteressesScreen}
        />

        <WithPageHeaderRoute
          path={ROUTES.CADASTRAR_POST}
          screen={CadastrarPostScreen}
          authenticate
        />

        <WithPageHeaderGoBackRoute
          path={`${ROUTES.COMENTARIOS_DE_POST}/:idPost`}
          screen={ExibirComentariosScreen}
        />

        <WithPageHeaderRoute
          path={`${ROUTES.EXIBIR_POST}/:idPost`}
          screen={ExibirPostScreen}
        />

        <WithPageHeaderRoute
          path={`${ROUTES.EDITAR_POST}/:idPost`}
          screen={EditarPostScreen}
          authenticate
        />

        <WithPageHeaderGoBackRoute
          path={`${ROUTES.ADICIONAR_POST_LISTA}/:idPost`}
          screen={AdicionarPostListaScreen}
          authenticate
        />

        <WithPageHeaderGoBackRoute
          path={`${ROUTES.EXIBIR_LISTA}/:idLista`}
          screen={ExibirListaScreen}
        />

        <WithPageHeaderRoute
          path={ROUTES.FAVORITOS}
          screen={FavoritosScreen}
          authenticate
        />

        <WithPageHeaderRoute
          path={ROUTES.FEITOS}
          screen={FeitosScreen}
          authenticate
        />

        <WithPageHeaderRoute
          path={ROUTES.EDITAR_PERFIL}
          screen={EditarPerfilScreen}
          authenticate
        />

        <WithPageHeaderRoute
          path={ROUTES.SUAS_LISTAS}
          screen={SuasListasScreen}
        />

        <WithPageHeaderGoBackRoute
          path={`${ROUTES.FILTRAGEM_RESULTADO}/:selected`}
          screen={FiltragemResultadosScreen}
        />

        <WithPageHeaderGoBackRoute
          path={`${ROUTES.FILTRAGEM}/:selected`}
          screen={FiltragemScreen}
        />

        <WithPageHeaderGoBackRoute
          path={ROUTES.FILTRAGEM}
          screen={FiltragemScreen}
        />

        <WithPageHeaderRoute
          path={`${ROUTES.PERFIL}/:idUsuario`}
          screen={PerfilScreen}
        />

        <WithPageHeaderGoBackRoute path={ROUTES.BUSCA} screen={BuscaScreen} />

        <WithPageHeaderRoute path={ROUTES.HOME} screen={HomeScreen} />

        <NotFoundRoute screen={NaoEncontradoScreen} />
      </Switch>

      <ToastMessage />
      <AuthRequiredMessage />
      <ApiErrorMessage />
    </>
  );
}

const errorMessage = 'Você precisa estar logado para acessar esta página';

function PublicRoute({ path, screen }) {
  return <Route path={path} component={screen} exact />;
}

function PrivateRoute({ path, screen, ...props }) {
  const apiErrorMessage = useApiErrorMessageManager();
  const [user] = useGlobalUser();
  const { goBack } = useHistory();

  if (!user) {
    apiErrorMessage.send(errorMessage);
    goBack();
    return;
  } else if (screen) {
    return <Route path={path} component={screen} {...props} exact />;
  } else {
    return <Route path={path} {...props} exact />;
  }
}

function WithPageHeaderRoute({ path, screen, authenticate }) {
  const Screen = screen;
  const apiErrorMessage = useApiErrorMessageManager();
  const [user] = useGlobalUser();

  if (authenticate && !user) {
    apiErrorMessage.send(errorMessage);
    return <Redirect to={ROUTES.AUTENTICACAO} />;
  } else {
    return (
      <Route path={path} exact>
        <PageHeader />
        <Screen />
      </Route>
    );
  }
}

function WithPageHeaderGoBackRoute({ path, screen, authenticate }) {
  const Screen = screen;
  const apiErrorMessage = useApiErrorMessageManager();
  const [user] = useGlobalUser();

  if (authenticate && !user) {
    apiErrorMessage.send(errorMessage);
    return <Redirect to={ROUTES.AUTENTICACAO} />;
  } else {
    return (
      <Route path={path} exact>
        <PageHeaderGoBack />
        <Screen />
      </Route>
    );
  }
}

function NotFoundRoute({ screen }) {
  return <Route component={screen} exact />;
}
