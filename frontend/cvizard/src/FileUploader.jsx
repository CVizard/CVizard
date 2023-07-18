import React, { useState } from "react";
import axios from "axios";

export function FileUploader() {
  const [file, setFile] = useState(null);
  const [responseData, setResponseData] = useState(null);

  const onFileChange = (event) => {
    setFile(event.target.files[0]);
  };

  const onFileUpload = () => {
    const formData = new FormData();
    formData.append("pdf_file", file);
    axios
      .post("http://localhost:8081/api/reader", formData)
      .then((response) => {
        console.log(response);
        setResponseData(response.data.file_id);
      })
      .catch((error) => {
        console.error("There was an error!", error);
      });
    formData.append("pdf_file", file);
  };

  return (
    <div className="flex justify-center items-center">
      <div className="text-white border-solid border-8 w-max flex items-center bg-blue-500 px-6 py-6 border-blue-400 font-thin">
        <input type="file" onChange={onFileChange} accept="application/pdf" />
        <button className="bg-blue-400 hover:bg-blue-300 py-2 px-2 rounded" onClick={onFileUpload}>Upload!</button>
      </div>
      {responseData && ( // Render the response data if available
        <div>
          <h2>File ID:</h2>
          <pre>{JSON.stringify(responseData, null, 2)}</pre>
        </div>
      )}
    </div>
  );
}
