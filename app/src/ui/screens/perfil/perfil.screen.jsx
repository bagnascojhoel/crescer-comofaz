import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { useGlobalUser } from '../../../context';
import {
  useApiErrorMessageManager,
  usePrivadoUsuarioApi,
  usePublicoUsuarioApi,
} from '../../../hooks';
import { ROUTES } from '../../../routes';
import {
  Container,
  LayoutRow,
  LightButton,
  Loader,
  TabsHeader,
  VerticalSpacing,
} from '../../components';
import { PerfilCreations } from './perfil-creations/perfil-creations.component';
import { PerfilHeader } from './perfil-header/perfil-header.component';
import { PerfilInteresses } from './perfil-interesses/perfil-interesses.component';
import { PerfilListas } from './perfil-listas/perfil-listas.component';

export function PerfilScreen() {
  const { idUsuario } = useParams();
  const apiErrorMessage = useApiErrorMessageManager();

  const { exibirMeuPerfil } = usePrivadoUsuarioApi();
  const { exibirPerfil } = usePublicoUsuarioApi();

  const [globalUser] = useGlobalUser();
  const [currentTab, setCurrentTab] = useState(0);
  const [usuario, setUsuario] = useState();

  const isMyProfile =
    globalUser && globalUser.idUsuario === parseInt(idUsuario);

  const handleTabClick = (tabIndex) => {
    setCurrentTab(tabIndex);
  };

  useEffect(() => {
    (async () => {
      let response;

      if (isMyProfile) response = await exibirMeuPerfil();
      else response = await exibirPerfil(idUsuario);

      if (response.error) apiErrorMessage.send(response.error);
      else setUsuario(response);
    })();
  }, [globalUser]);

  if (!usuario) return <Loader fullScreen large />;

  return (
    <>
      <Container>
        {isMyProfile && (
          <>
            <LayoutRow alignEnd>
              <LightButton to={ROUTES.EDITAR_PERFIL}>Editar perfil</LightButton>
            </LayoutRow>
            <VerticalSpacing />
          </>
        )}
      </Container>

      <PerfilHeader
        name={usuario.nome}
        photo={usuario.foto}
        contributions={usuario.totalContribuicoes}
      />

      <PerfilInteresses interesses={usuario.interesses} />

      <TabsHeader
        tabs={['criações', 'listas']}
        onTabClick={handleTabClick}
        currentTab={currentTab}
      />

      {currentTab === 0 ? (
        <PerfilCreations posts={usuario.posts} />
      ) : (
        <PerfilListas listas={usuario.listas} />
      )}
    </>
  );
}
