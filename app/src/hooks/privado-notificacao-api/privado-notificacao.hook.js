import API_CONTEXT from '../api-context';
import { usePrivadoBaseApi } from '../privado-base-api/privado-base-api.hook';

export function usePrivadoNotificacaoApi() {
  const { get, put } = usePrivadoBaseApi(API_CONTEXT.NOTIFICACAO);

  const buscarNovasNotificacoes = async () =>
    await get();

  const atualizarNotificacao = async (idNotificacao) =>
    await put(`/${idNotificacao}`);

  return { buscarNovasNotificacoes, atualizarNotificacao };
}
