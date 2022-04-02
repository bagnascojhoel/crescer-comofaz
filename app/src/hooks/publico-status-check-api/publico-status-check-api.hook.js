import API_CONTEXT from '../api-context';
import { useBaseApi } from '../base-api/base-api.hook';

export function usePublicoStatusCheckApi() {
  const { get } = useBaseApi(`publico/${API_CONTEXT.STATUS}`);

  const checkStatus = async () => await get();

  return { checkStatus };
}
