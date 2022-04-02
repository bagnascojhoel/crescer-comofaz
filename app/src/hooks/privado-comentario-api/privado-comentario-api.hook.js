import { usePrivadoBaseApi } from '../privado-base-api/privado-base-api.hook';
import API_CONTEXT from '../api-context';

export function usePrivadoComentarioApi() {
  const { post } = usePrivadoBaseApi(API_CONTEXT.COMENTARIO);

  const cadastrarComentario = async ({ idPost, formData }) =>
    await post(`/cadastrar/${idPost}`, formData);

  return { cadastrarComentario };
}
