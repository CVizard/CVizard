import React, { useState } from 'react';
import { Document, Page } from 'react-pdf';
import axios from "axios";

export const PdfViewer = () => {
  const [fileId, setFileId] = useState('');
  const [file, setFile] = useState(null);

  const handleButtonClick = () => {
    // Assuming you're using a library like axios for HTTP requests
    // Make the GET request to fetch the PDF file
    axios
      .get(`url?key=${fileId}`)
      .then(response => {
        // Handle the response, e.g., display the PDF
        setFile(URL.createObjectURL(new Blob([response.data], { type: 'application/pdf' })));
      })
      .catch(error => {
        // Handle any errors
        console.error('Error fetching PDF:', error);
      });
  };

  return (
    <div>
      <input
        type="text"
        value={fileId}
        onChange={event => setFileId(event.target.value)}
      />
      <button onClick={handleButtonClick}>Fetch PDF</button>
      <div>
      <Document file={file}>
        <Page pageNumber={1} />
      </Document>
    </div>
    </div>
  );
};
