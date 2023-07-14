import spacy
import os
from spacy.matcher import Matcher
import re
import strenum


PHONE_REGEX = r'(?:(?:(?:\+|00)?48)|(?:\(\+?48\)))?(?:1[2-8]|2[2-69]|3[2-49]|4[1-8]|5[0-9]|6[0-35-9]|[7-8][1-9]|9[145])\d{7}'
ENTITY_TYPES = ['persName', 'geogName']

replacement = "[private]"

class Engine(strenum.StrEnum):
    PL = 'pl_core_news_lg'
    EN = 'en_core_web_lg'


def anonymize_text(text: str, engine: str) -> str:
    nlp = spacy.load(engine)
    doc = nlp(text)

    email_pattern = [{"LIKE_EMAIL": True}]
    url_pattern = [{"LIKE_URL": True}]

    matcher = Matcher(nlp.vocab)
    matcher.add("EMAIL", [email_pattern])
    matcher.add("URL", [url_pattern])

    matches = [str(match) for match in matcher(doc, as_spans=True)]
    matches += re.findall(PHONE_REGEX, text)

    anonymized_text = text

    for ent in doc.ents:
        if ent.label_ in ENTITY_TYPES:
            anonymized_text = anonymized_text.replace(ent.text, replacement)

    for match in matches:
        anonymized_text = anonymized_text.replace(match, replacement)
    
    return anonymized_text