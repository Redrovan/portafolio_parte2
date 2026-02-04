from sqlalchemy.orm import Session
import models

class NotificationRepository:

    def __init__(self, db: Session):
        self.db = db

    def find_all(self):
        return self.db.query(models.Notification).all()

    def find_pending(self):
        return self.db.query(models.Notification)\
            .filter(models.Notification.status == "PENDING")\
            .all()

    def save(self, notification):
        self.db.add(notification)
        self.db.commit()
        self.db.refresh(notification)
        return notification
