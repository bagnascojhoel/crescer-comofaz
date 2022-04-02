import React, { useEffect, useState } from 'react';
import {
  useApiErrorMessageManager,
  usePrivadoUsuarioApi,
} from '../../../hooks';
import {
  Container,
  CriarListaForm,
  ListGallery,
  Loader,
  Title,
  VerticalSpacing,
} from '../../components';
import { useHistory } from 'react-router-dom';
import { ROUTES } from '../../../routes';

export function SuasListasScreen() {
  const { push } = useHistory();
  const apiErrorMessage = useApiErrorMessageManager();

  const { exibirListas } = usePrivadoUsuarioApi();

  const [listas, setListas] = useState(null);

  const handleClickLista = (idLista) => {
    push(`${ROUTES.EXIBIR_LISTA}/${idLista}`);
  };

  const handleCriada = (novaLista) => {
    const listasAtualizadas = [novaLista, ...listas];
    setListas(listasAtualizadas);
  };

  useEffect(() => {
    (async () => {
      const response = await exibirListas();

      if (response.error) apiErrorMessage.send(response.error);
      else setListas(response);
    })();
  }, []);

  return (
    <>
      <Container>
        <Title>Suas listas</Title>
        <CriarListaForm onCriada={handleCriada} />
        <VerticalSpacing />

        <VerticalSpacing largeGutter />
      </Container>

      {!listas ? (
        <Loader cover />
      ) : (
        <ListGallery
          data={listas}
          onClickItem={handleClickLista}
          emptyMessage="Você ainda não possui nenhuma lista"
        />
      )}
    </>
  );
}
