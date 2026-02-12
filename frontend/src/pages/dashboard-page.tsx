import { useRoles } from "../hooks/use-roles";
import { useNavigate } from "react-router";
import { useEffect } from "react";

const DashboardPage: React.FC = () => {
  const { isLoading, isOrganizer, isStaff } = useRoles();
  const navigate = useNavigate();

  useEffect(() => {
    if (isLoading) return;

    if (isOrganizer) {
      navigate("/dashboard/events");
    } else if (isStaff) {
      navigate("/dashboard/validate-qr");
    } else {
      navigate("/dashboard/tickets");
    }
  }, [isLoading, isOrganizer, isStaff, navigate]);

  return <p>Loading...</p>;
};

export default DashboardPage;
