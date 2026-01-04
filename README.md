# Satu-Sehat-Products
Satu sehat Products backend only

crud products Satu Sehat 
Auth0 for aunthentication & authorization
file upload aws s3


Please read product satu sehat.docx to see get token from satu sehat and Auth0 

1. Login Auth0 → https://manage.auth0.com
2. Create API
3. Name: HealthApp API
4. Identifier: https://api.healthapp.com
5. Algorithm: RS256
6. Create permissions:
    - read:products
    - write:products
    - delete:products
7. Create Application
8. Name: healthapp-backend-client
9. Type: Machine to Machine
10. Authorize Application ke API
11. Grant all permissions
12. Ambil Auth0 Domain
13. issuer-uri = https://YOUR_DOMAIN.auth0.com/
14. audience = https://api.healthapp.com
15. Pasang di Spring Boot config
