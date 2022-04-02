import API_CONTEXT from '../api-context';
import { useBaseApi } from '../base-api/base-api.hook';

export function usePublicoCategoriaApi() {
  const { get } = useBaseApi('publico/' + API_CONTEXT.CATEGORIA);

  const buscarCategorias = async () => await get();

  return { buscarCategorias };
}
