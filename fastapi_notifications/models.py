from sqlalchemy import Column, Integer, String, DateTime
from database import Base
from datetime import datetime

class Notification(Base):
    __tablename__ = "notifications"

    id = Column(Integer, primary_key=True, index=True)
    phone = Column(String)
    message = Column(String)
    scheduled_time = Column(DateTime)
    status = Column(String, default="PENDING")
