FROM python:3.11-slim

WORKDIR /app
COPY . .
RUN pip install -r book_catalog/requirements.txt
EXPOSE 8081
CMD ["uvicorn", "book_catalog.main:app", "--host", "0.0.0.0", "--port", "8081"]
