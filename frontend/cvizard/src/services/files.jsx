export async function uploadFile(file) {
  const response = await axios.post("https://localhost:8080/api", file);
  return response.data;
}
