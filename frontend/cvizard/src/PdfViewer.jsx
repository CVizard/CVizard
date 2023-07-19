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
          key: fileId,
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
    <div>
      <input
        type="text"
        value={fileId}
        onChange={(event) => setFileId(event.target.value)}
      />
      <button onClick={downloadPdf}>Fetch PDF</button>
      <div>
        <Document file={file}>
          <Page pageNumber={1} />
        </Document>
      </div>
    </div>
  );
};
