import pytesseract
from pdf2image.pdf2image import convert_from_bytes
from PIL import Image
from tempfile import TemporaryDirectory
from pypdf import PdfReader


PDF_PATH = 'pdf/cv_example.pdf'
TEXT_TESS_PATH = 'text_tesseract.txt'
TEXT_PYPDF_PATH = 'text_pypdf.txt'
TESSERACT_OPTIONS = '-l eng+pol'


def pdf_to_text_tesseract(pdf_bytes: bytes, tesseract_options=TESSERACT_OPTIONS) -> str:
    with TemporaryDirectory() as tempdir:
        pdf_pages = convert_from_bytes(pdf_bytes, fmt='png', output_folder=tempdir)
        image_file_list = []
        text = ''

        for page_enumeration, page in enumerate(pdf_pages):
            image_path = f'{tempdir}/{page_enumeration}.png'
            page.save(image_path, 'PNG')
            image_file_list.append(image_path)


        for image_file in image_file_list:
            page_text = pytesseract.image_to_string(Image.open(image_file), config=tesseract_options)
            text += page_text

        return text


def pdf_to_text_pypdf(path_to_pdf: str) -> str:
    pdf = PdfReader(path_to_pdf)
    text = ''

    for page in pdf.pages:
        text += page.extract_text()

    return text

if __name__ == '__main__':
    text_tesseract = pdf_to_text_tesseract(PDF_PATH, TESSERACT_OPTIONS)
    text_pypdf = pdf_to_text_pypdf(PDF_PATH)

    with open(TEXT_TESS_PATH, 'w') as file:
        file.write(text_tesseract)

    with open(TEXT_PYPDF_PATH, 'w') as file:
        file.write(text_pypdf)