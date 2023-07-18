import { PdfViewer } from "./PdfViewer";
import { FileUploader } from "./FileUploader";

import "./styles.css";
export default function App() {
  return (
    <>
      <FileUploader />
      <PdfViewer />
    </>
  );
}
