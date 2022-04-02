import API_CONTEXT from '../api-context';
import { useBaseApi } from '../base-api/base-api.hook';

export function usePublicoListaApi() {
  const { get } = useBaseApi(`publico/${API_CONTEXT.LISTA}`);

  const buscarLista = async ({ idLista }) => {
    return await get(`/${idLista}`);
  };
  return {
    buscarLista,
  };
}
