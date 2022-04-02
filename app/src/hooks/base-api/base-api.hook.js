import axios from 'axios';

export function useBaseApi(context, headers) {
  const baseApi = axios.create({
    baseURL: `${process.env.REACT_APP_API_URL}/como-faz/${context}`,
    headers,
  });

  const method = async (_method, ...parameters) => {
    try {
      const response = await _method(...parameters);

      return response.data;
    } catch (error) {
      if (!error.response)
        return {
          error: 'Não foi possível conectar ao servidor',
        };
      else return error.response.data;
    }
  };

  const get = async (path) => await method(baseApi.get, path);

  const post = async (path, body) => await method(baseApi.post, path, body);

  const put = async (path, body) => await method(baseApi.put, path, body);

  return {
    post,
    get,
    put,
  };
}
