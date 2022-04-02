import API_CONTEXT from '../api-context';
import { usePrivadoBaseApi } from '../privado-base-api/privado-base-api.hook';

export function usePrivadoCategoriaApi() {
  const { get } = usePrivadoBaseApi(API_CONTEXT.CATEGORIA);

  const buscarCategorias = async () => await get();

  return { buscarCategorias };
}
