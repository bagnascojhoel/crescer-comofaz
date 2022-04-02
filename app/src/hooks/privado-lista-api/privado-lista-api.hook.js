import API_CONTEXT from '../api-context';
import { usePrivadoBaseApi } from '../privado-base-api/privado-base-api.hook';

export function usePrivadoListaApi() {
  const { post, get } = usePrivadoBaseApi(API_CONTEXT.LISTA);

  const cadastrarLista = async ({ assunto, isPrivado }) =>
    await post('/cadastrar', { assunto, isPrivado });

  const atualizarPostLista = async ({ idLista, idPost }) =>
    await post(`/adicionar/?idLista=${idLista}&idPost=${idPost}`);

  const buscarLista = async ({ idLista }) => await get(`/${idLista}`);

  return { cadastrarLista, atualizarPostLista, buscarLista };
}
