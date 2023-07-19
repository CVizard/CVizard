import React, { useState } from "react";
import axios from "axios";

export function FileUploader() {
  const [file, setFile] = useState(null);
  const [responseData, setResponseData] = useState(null);
  const [feedback, setFeedback] = useState("");

  const onFileChange = (event) => {
    setFile(event.target.files[0]);
  };

  const onFileUpload = () => {
    const formData = new FormData();
    setFeedback("Uploading ...");
    formData.append("pdf_file", file);
    axios
      .post("http://localhost:8080/api/reader", formData)
      .then((response) => {
        console.log(response);
        setResponseData(response.data.file_id);
        localStorage.setItem("file_id", response.data.file_id);
        setFeedback("Uploaded!");
      })
      .catch((error) => {
        console.error("There was an error!", error);
        setFeedback("Error!");
      });
    formData.append("pdf_file", file);
  };

  return (
    <div className="flex flex-col justify-center items-center">
      <div className="text-white border-solid border-8 w-max flex items-center bg-blue-500 px-6 py-6 border-blue-400 font-thin">
        <input type="file" onChange={onFileChange} accept="application/pdf" />
        <button
          className="bg-blue-400 hover:bg-blue-300 py-2 px-2 rounded"
          onClick={onFileUpload}
        >
          Upload!
        </button>
      </div>
      <div className="h-32">
        <p>
          {feedback && (
            <p>
              Status: <br />
              {feedback}
            </p>
          )}
        </p>
      </div>
    </div>
  );
}
