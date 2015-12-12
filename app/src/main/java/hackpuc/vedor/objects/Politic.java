package hackpuc.vedor.objects;

import com.parse.ParseObject;

import hackpuc.vedor.utils.ParseFields;

/**
 * Created by JGabrielFreitas on 12/12/15.
 */
public class Politic extends ParseFields{

    private String candidateName;
    private String candidateNumber;
    private String electionYear;
    private String compositionLegend;
    private String candidateDocumentNumber;
    private String turnNumber;
    private String candidatePartyNumber;
    private String candidateUf;
    private String candidateOffice;
    private String candidateUrnName;
    private String candidatePartyName;
    private String candidateTurnDescription;
    private String candidateEmail;

    public Politic() {}

    public Politic(ParseObject parseObject) {

        setCandidateName((String) parseObject.get(POLITIC_NAME));
        setCandidateNumber((String) parseObject.get(POLITIC_NUM));
        setElectionYear((String) parseObject.get(POLITIC_YEAR));
        setCompositionLegend((String) parseObject.get(POLITIC_LEGEND));
        setCandidateDocumentNumber((String) parseObject.get(POLITIC_CPF));
        setTurnNumber((String) parseObject.get(POLITIC_NUM_TURNO));
        setCandidatePartyNumber((String) parseObject.get(POLITIC_NUM_PART));
        setCandidateUf((String) parseObject.get(POLITIC_UF));
        setCandidateOffice((String) parseObject.get(POLITIC_CARGO));
        setCandidateUrnName((String) parseObject.get(POLITIC_URNA));
        setCandidatePartyName((String) parseObject.get(POLITIC_NOME_PART));
        setCandidateTurnDescription((String) parseObject.get(POLITIC_DESC_TURN));
        setCandidateEmail((String) parseObject.get(POLITIC_EMAIL));
    }

    public String getCandidatePartyName() {
        return candidatePartyName;
    }

    public void setCandidatePartyName(String candidatePartyName) {
        this.candidatePartyName = candidatePartyName;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getCandidateNumber() {
        return candidateNumber;
    }

    public void setCandidateNumber(String candidateNumber) {
        this.candidateNumber = candidateNumber;
    }

    public String getElectionYear() {
        return electionYear;
    }

    public void setElectionYear(String electionYear) {
        this.electionYear = electionYear;
    }

    public String getCompositionLegend() {
        return compositionLegend;
    }

    public void setCompositionLegend(String compositionLegend) {
        this.compositionLegend = compositionLegend;
    }

    public String getCandidateDocumentNumber() {
        return candidateDocumentNumber;
    }

    public void setCandidateDocumentNumber(String candidateDocumentNumber) {
        this.candidateDocumentNumber = candidateDocumentNumber;
    }

    public String getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(String turnNumber) {
        this.turnNumber = turnNumber;
    }

    public String getCandidatePartyNumber() {
        return candidatePartyNumber;
    }

    public void setCandidatePartyNumber(String candidatePartyNumber) {
        this.candidatePartyNumber = candidatePartyNumber;
    }

    public String getCandidateUf() {
        return candidateUf;
    }

    public void setCandidateUf(String candidateUf) {
        this.candidateUf = candidateUf;
    }

    public String getCandidateOffice() {
        return candidateOffice;
    }

    public void setCandidateOffice(String candidateOffice) {
        this.candidateOffice = candidateOffice;
    }

    public String getCandidateUrnName() {
        return candidateUrnName;
    }

    public void setCandidateUrnName(String candidateUrnName) {
        this.candidateUrnName = candidateUrnName;
    }

    public String getCandidateTurnDescription() {
        return candidateTurnDescription;
    }

    public void setCandidateTurnDescription(String candidateTurnDescription) {
        this.candidateTurnDescription = candidateTurnDescription;
    }

    public String getCandidateEmail() {
        return candidateEmail;
    }

    public void setCandidateEmail(String candidateEmail) {
        this.candidateEmail = candidateEmail;
    }

    public String toString() {
        return "Politic{" +
                "candidateName='" + candidateName + '\'' +
                ", candidateNumber='" + candidateNumber + '\'' +
                ", electionYear='" + electionYear + '\'' +
                ", compositionLegend='" + compositionLegend + '\'' +
                ", candidateDocumentNumber='" + candidateDocumentNumber + '\'' +
                ", turnNumber='" + turnNumber + '\'' +
                ", candidatePartyNumber='" + candidatePartyNumber + '\'' +
                ", candidateUf='" + candidateUf + '\'' +
                ", candidateOffice='" + candidateOffice + '\'' +
                ", candidateUrnName='" + candidateUrnName + '\'' +
                ", candidatePartyName='" + candidatePartyName + '\'' +
                ", candidateTurnDescription='" + candidateTurnDescription + '\'' +
                ", candidateEmail='" + candidateEmail + '\'' +
                '}';
    }
}
