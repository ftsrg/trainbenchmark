import smtplib
from email.mime.text import MIMEText

def send_mail(email):
    address = email["address"]
    password = email["password"]
    host = email["host"]

    msg = MIMEText("<helpful information about the run>")
    msg["Subject"] = "Train Benchmark measurement ready"
    msg["From"] = address
    msg["To"] = address
    
    session = smtplib.SMTP(host)	 
    session.ehlo()
    session.starttls()
    session.login(address, password)
    session.sendmail(from_addr=address, to_addrs=[address], msg=msg.as_string())
