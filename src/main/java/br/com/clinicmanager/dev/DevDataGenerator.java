package br.com.clinicmanager.dev;

import br.com.clinicmanager.clinics.entity.Clinic;
import br.com.clinicmanager.clinics.enums.ClinicStatus;
import br.com.clinicmanager.clinics.repository.ClinicRepository;
import br.com.clinicmanager.enums.Shift;
import br.com.clinicmanager.enums.Specialty;
import br.com.clinicmanager.plans.entity.Plan;
import br.com.clinicmanager.plans.enums.PlanType;
import br.com.clinicmanager.plans.repository.PlanRepository;
import br.com.clinicmanager.users.entity.Doctor;
import br.com.clinicmanager.users.entity.Patient;
import br.com.clinicmanager.users.entity.Receptionist;
import br.com.clinicmanager.users.entity.User;
import br.com.clinicmanager.users.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DevDataGenerator {

    @Inject
    PlanRepository planRepository;

    @Inject
    ClinicRepository clinicRepository;

    @Inject
    UserRepository userRepository;

    @PostConstruct
    public void generateData() {
        // Criar Planos
        if (planRepository.count() == 0) {
            Plan freePlan = new Plan("Free Plan", PlanType.GRATUITO, "Plano gratuito", 0.00, "1 mês");
            Plan basicPlan = new Plan("Basic Plan", PlanType.BASICO, "Plano básico", 99.99, "1 mês");
            Plan advancedPlan = new Plan("Advanced Plan", PlanType.AVANCADO, "Plano avançado", 149.99, "6 meses");
            Plan premiumPlan = new Plan("Premium Plan", PlanType.PREMIUM, "Plano premium", 199.99, "1 ano");
            planRepository.persist(freePlan);
            planRepository.persist(basicPlan);
            planRepository.persist(advancedPlan);
            planRepository.persist(premiumPlan);
        }

        // Criar Clínicas
        if (clinicRepository.count() == 0) {
            Plan freePlan = planRepository.find("name", "Free Plan").firstResult();
            Plan basicPlan = planRepository.find("name", "Basic Plan").firstResult();
            Plan advancedPlan = planRepository.find("name", "Advanced Plan").firstResult();
            Plan premiumPlan = planRepository.find("name", "Premium Plan").firstResult();

            Clinic clinic1 = new Clinic("Clinic A", "Endereço A", "123456789", "clinica@example.com", freePlan, "12345678000101", ClinicStatus.ACTIVE);
            Clinic clinic2 = new Clinic("Clinic B", "Endereço B", "987654321", "clinicb@example.com", basicPlan, "10987654000101", ClinicStatus.ACTIVE);
            Clinic clinic3 = new Clinic("Clinic C", "Endereço C", "192837465", "clinicc@example.com", advancedPlan, "11223344000101", ClinicStatus.INACTIVE);
            Clinic clinic4 = new Clinic("Clinic D", "Endereço D", "564738291", "clinicd@example.com", premiumPlan, "22334455000101", ClinicStatus.ACTIVE);
            clinicRepository.persist(clinic1);
            clinicRepository.persist(clinic2);
            clinicRepository.persist(clinic3);
            clinicRepository.persist(clinic4);
        }

        // Criar Usuários
        if (userRepository.count() == 0) {
            // Admin User
            User admin = new User("Admin", "admin@example.com", "adminpass");
            userRepository.persist(admin);

            // Doctors
            Doctor doctor1 = new Doctor("Dr. Ana", "doctorA@example.com", "doctorpass", Specialty.CARDIOLOGY, "12345");
            Doctor doctor2 = new Doctor("Dr. Bruno", "doctorB@example.com", "doctorpass", Specialty.NEUROLOGY, "67890");
            Doctor doctor3 = new Doctor("Dr. Carla", "doctorC@example.com", "doctorpass", Specialty.DERMATOLOGY, "54321");
            Doctor doctor4 = new Doctor("Dr. Daniel", "doctorD@example.com", "doctorpass", Specialty.ORTHOPEDICS, "98765");
            Doctor doctor5 = new Doctor("Dr. Elisa", "doctorE@example.com", "doctorpass", Specialty.PEDIATRICS, "11223");
            userRepository.persist(doctor1);
            userRepository.persist(doctor2);
            userRepository.persist(doctor3);
            userRepository.persist(doctor4);
            userRepository.persist(doctor5);

            // Patients
            Patient patient1 = new Patient("Paciente A", "patientA@example.com", "patientpass", "Sem histórico médico", "Nenhuma", "Nenhuma");
            Patient patient2 = new Patient("Paciente B", "patientB@example.com", "patientpass", "Alérgico a penicilina", "Penicilina", "Aspirina");
            userRepository.persist(patient1);
            userRepository.persist(patient2);

            // Receptionists
            Receptionist receptionist1 = new Receptionist("Recepcionista A", "receptionistA@example.com", "receptionistpass", Shift.MORNING);
            Receptionist receptionist2 = new Receptionist("Recepcionista B", "receptionistB@example.com", "receptionistpass", Shift.AFTERNOON);
            Receptionist receptionist3 = new Receptionist("Recepcionista C", "receptionistC@example.com", "receptionistpass", Shift.EVENING);
            Receptionist receptionist4 = new Receptionist("Recepcionista D", "receptionistD@example.com", "receptionistpass", Shift.NIGHT);
            userRepository.persist(receptionist1);
            userRepository.persist(receptionist2);
            userRepository.persist(receptionist3);
            userRepository.persist(receptionist4);
        }
    }
}
