import React, { useState } from "react";
import { Document, Page } from "react-pdf";
import FileSaver from "file-saver";
import axios from "axios";

export const PdfViewer = () => {
  const [fileId, setFileId] = useState("");
  const [file, setFile] = useState(null);

  const handleButtonClick = () => {
    // Assuming you're using a library like axios for HTTP requests
    // Make the GET request to fetch the PDF file
    axios
      .get(`http://localhost:8080/api/creator?key=${fileId}`)
      .then((response) => {
        // Handle the response, e.g., display the PDF
        setFile(
          URL.createObjectURL(
            new Blob([response.data], { type: "application/pdf" })
          )
        );
      })
      .catch((error) => {
        // Handle any errors
        console.error("Error fetching PDF:", error);
      });
  };
  const downloadPdf = () => {
    axios
      .get("http://localhost:8080/api/creator", {
        params: {
          key: localStorage.getItem("file_id"),
        },
        responseType: "blob", // Important
      })
      .then((response) => {
        FileSaver.saveAs(new Blob([response.data]), "output.pdf");
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <div className="flex justify-center items-center">
      <button
        className="text-white border-solid border-8 w-max flex items-center bg-blue-500 px-6 py-6 border-blue-400 font-thin mt-10"
        onClick={downloadPdf}
      >
        Download PDF
      </button>
    </div>
  );
};
