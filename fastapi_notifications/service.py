from datetime import datetime
from repository import NotificationRepository
import models, schemas

class NotificationService:

    def __init__(self, db):
        self.repo = NotificationRepository(db)

    def create(self, data: schemas.NotificationCreate):

        notification = models.Notification(
            phone=data.phone,
            message=data.message,
            scheduled_time=data.scheduled_time
        )

        return self.repo.save(notification)

    def get_all(self):
        return self.repo.find_all()

    def process_notifications(self):

        notifications = self.repo.find_pending()
        now = datetime.now()

        for n in notifications:

            if n.scheduled_time <= now:

                # 📲 SIMULACIÓN DE ENVÍO
                print(f"ENVIANDO WhatsApp a {n.phone}: {n.message}")

                n.status = "SENT"
                self.repo.save(n)
