from pydantic import BaseModel
from datetime import datetime
from typing import Optional

class NotificationBase(BaseModel):
    phone: str
    message: str
    scheduled_time: datetime

class NotificationCreate(NotificationBase):
    pass

class Notification(NotificationBase):
    id: int
    status: str

    class Config:
        from_attributes = True
