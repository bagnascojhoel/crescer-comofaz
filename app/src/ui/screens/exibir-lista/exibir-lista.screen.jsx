import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { useGlobalUser } from '../../../context';
import {
  useApiErrorMessageManager,
  usePrivadoListaApi,
  usePublicoListaApi,
} from '../../../hooks';
import {
  Container,
  Loader,
  PostList,
  Subtitle,
  VerticalSpacing,
} from '../../components';

export function ExibirListaScreen() {
  const apiErrorMessage = useApiErrorMessageManager();
  const privadoListApi = usePrivadoListaApi();
  const publicoListApi = usePublicoListaApi();
  const { idLista } = useParams();

  const [globalUser] = useGlobalUser();
  const [lista, setLista] = useState();

  useEffect(() => {
    (async () => {
      let response;
      if (globalUser) {
        response = await privadoListApi.buscarLista({ idLista });
      } else {
        response = await publicoListApi.buscarLista({ idLista });
      }

      if (response.error) apiErrorMessage.send(response.error);
      else setLista(response);
    })();
  }, []);

  if (!lista) return <Loader fullScreen large />;

  return (
    <Container>
      <Subtitle>{lista.assunto}</Subtitle>

      <VerticalSpacing />

      <PostList
        dataList={lista.posts}
        keyExtractor={({ idPost }) => idPost}
        emptyMessage="Esta lista ainda está vazia"
      />
    </Container>
  );
}
