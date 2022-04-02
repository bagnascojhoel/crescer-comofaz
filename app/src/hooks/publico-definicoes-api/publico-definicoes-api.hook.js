import API_CONTEXT from '../api-context';
import { useBaseApi } from '../base-api/base-api.hook';

export function usePublicoDefinicoesApi() {
  const { get } = useBaseApi(`publico/${API_CONTEXT.DEFINICOES}`);

  const listarDefinicoes = async () => await get();

  return { listarDefinicoes };
}
