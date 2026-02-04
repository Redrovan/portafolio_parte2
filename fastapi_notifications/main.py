from fastapi import FastAPI, Depends
from sqlalchemy.orm import Session
from typing import List

import database, models, schemas, service

from fastapi.middleware.cors import CORSMiddleware

models.Base.metadata.create_all(bind=database.engine)

app = FastAPI(title="Notification Service")

# CORS
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_methods=["*"],
    allow_headers=["*"],
)

def get_service(db: Session = Depends(database.get_db)):
    return service.NotificationService(db)

@app.post("/api/notifications", response_model=schemas.Notification)
def create_notification(
    notification: schemas.NotificationCreate,
    service=Depends(get_service)
):
    return service.create(notification)

@app.get("/api/notifications", response_model=List[schemas.Notification])
def list_notifications(service=Depends(get_service)):
    return service.get_all()

@app.post("/api/notifications/process")
def process(service=Depends(get_service)):
    service.process_notifications()
    return {"message": "Processed"}
