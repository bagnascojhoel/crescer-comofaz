import { usePrivadoBaseApi } from '../privado-base-api/privado-base-api.hook';
import API_CONTEXT from '../api-context';

export function usePrivadoRespostaApi() {
  const { post } = usePrivadoBaseApi(API_CONTEXT.RESPOSTA);

  const cadastrarResposta = async ({ idComentario, texto }) =>
    await post(`/cadastrar/${idComentario}/?texto=${texto}`);

  return { cadastrarResposta };
}
