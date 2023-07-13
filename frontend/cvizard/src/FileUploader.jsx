import React, { useState } from "react";
import axios from "axios";

export function FileUploader() {
  const [file, setFile] = useState(null);

  const onFileChange = (event) => {
    setFile(event.target.files[0]);
  };

  const onFileUpload = () => {
    const formData = new FormData();
    formData.append("file", file);
    axios
      .post("http://localhost:8080/api", formData)
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.error("There was an error!", error);
      });
  };

  return (
    <div>
      <h3>File Upload:</h3>
      <div>
        <input type="file" onChange={onFileChange} accept="application/pdf" />
        <button onClick={onFileUpload}>Upload!</button>
      </div>
    </div>
  );
}

// export default FileUploader;
