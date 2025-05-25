FROM python:3.11-slim

WORKDIR /app
COPY . ./book_catalog
COPY book_catalog/requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt
EXPOSE 8081
CMD ["uvicorn", "book_catalog.main:app", "--host", "0.0.0.0", "--port", "8081"]


