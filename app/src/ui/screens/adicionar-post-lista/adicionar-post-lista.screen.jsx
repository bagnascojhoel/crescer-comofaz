import './styles.css';
import React, { useEffect, useState } from 'react';
import { useHistory, useParams } from 'react-router-dom';
import {
  useApiErrorMessageManager,
  usePrivadoListaApi,
  usePrivadoUsuarioApi,
  useToastMessageManager,
} from '../../../hooks';
import {
  BaseButton,
  Container,
  CriarListaForm,
  ListGallery,
  Loader,
  Modal,
  Subtitle,
  VerticalSpacing,
} from '../../components';

export function AdicionarPostListaScreen() {
  const { idPost } = useParams();
  const { goBack } = useHistory();
  const apiErrorMessage = useApiErrorMessageManager();
  const toastMessage = useToastMessageManager();
  const { atualizarPostLista } = usePrivadoListaApi();
  const { exibirListasSimplificadas } = usePrivadoUsuarioApi();

  const [showCriarLista, setShowCriarLista] = useState(false);
  const [listas, setListas] = useState(null);

  const handleShowCriarLista = () => setShowCriarLista(true);
  const handleHideCriarLista = () => setShowCriarLista(false);

  const handleAdicionar = async (idLista) => {
    const response = await atualizarPostLista({ idLista, idPost });

    if (response.error) {
      apiErrorMessage.send(response.error);
    } else {
      toastMessage.send('ComoFaz adicionado à sua lista');
      goBack();
    }
  };

  useEffect(() => {
    (async () => {
      const response = await exibirListasSimplificadas({ idPost });

      if (response.error) apiErrorMessage.send(response.error);
      else setListas(response);
    })();
  }, []);

  if (!listas) return <Loader fullScreen large />;

  return (
    <>
      <Container>
        <Subtitle>Adicionar à lista</Subtitle>
      </Container>

      <VerticalSpacing />

      <BaseButton
        onClick={handleShowCriarLista}
        className="adicionar-post-lista__criar-lista-button"
      >
        Criar nova lista
      </BaseButton>

      <ListGallery data={listas} onClickItem={handleAdicionar} />

      <Modal
        show={showCriarLista}
        onClose={handleHideCriarLista}
        className="adicionar-post-lista__criar-lista-form"
      >
        <CriarListaForm
          onCriada={handleAdicionar}
          mapper={({ idLista }) => idLista}
        />
      </Modal>
    </>
  );
}
